
package za.ac.tut.bl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.ac.tut.entity.City;
import za.ac.tut.entity.Mayor;

/**
 *
 * @author Thapelo Mkhwanazi
 */
public class CityManagerDB implements CityManagerInterface 
{
    
    private Connection connection;

    public CityManagerDB(String dbURL, String username, String password) throws SQLException 
    {
        connection =  DriverManager.getConnection(dbURL, username, password);
    }

   


    @Override
    public void add(City city, String sqlAddCity, String sqlAddMayor) throws SQLException
    {
        PreparedStatement ps = connection.prepareStatement(sqlAddCity);
        PreparedStatement ps2 = connection.prepareStatement(sqlAddMayor);
        
        ps.setInt(1, city.getId());
        ps.setString(2, city.getName());
        ps.setInt(3, city.getPopulation());
        
        ps2.setInt(1, city.getMayor().getId());
        ps2.setString(2, city.getMayor().getName());
        ps2.setString(3, city.getMayor().getSurname());
        ps2.setInt(4, city.getId());

        ps.executeUpdate();
        ps2.executeUpdate();

        ps.close();
        ps2.close(); 
        
    }

    @Override
    public City get(Integer id, String sql) throws SQLException 
    {
      PreparedStatement ps = connection.prepareStatement(sql);
      
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
        City city = null;

        if(rs.next())
        {
            Integer cityID = rs.getInt("id");
            String name = rs.getString("name");
            Integer population = rs.getInt("population");
            Integer mayorID = rs.getInt("mayor_id");
            String mayorName = rs.getString("mayor_name");
            String mayorSurname = rs.getString("mayor_surname");
            Mayor mayor = new Mayor(mayorID,mayorName, mayorSurname);
            city = new City(cityID, name, population, mayor);
        }
        ps.close();
        return city;
      
    }

    @Override
    public List<City> getAll(String sql) throws SQLException 
    {
        List<City> cities = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next())
        {
           int cityId = rs.getInt("id");
           String cityname = rs.getString("name");
           int population = rs.getInt("population");
           
           int mayorid = rs.getInt("mayor_id");
           String mayorName = rs.getString("mayor_name");
           String mayorSurname = rs.getString("mayor_surname");
            
           Mayor mayor = new Mayor(mayorid, mayorName, mayorSurname);
           City city = new City(cityId, cityname, population, mayor);
           cities.add(city);
           
           
        }
        
        ps.close();
        return cities;
    }

    @Override
    public boolean update(City city, String sql) throws SQLException 
    {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, city.getMayor().getName());
        ps.setString(2, city.getMayor().getSurname());
        ps.setInt(3, city.getId());

        ps.executeUpdate();
        ps.close();

        return true;
    }

    @Override
    public boolean delete(Integer cityID, String sql) throws SQLException 
    {
      PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, cityID);

        ps.executeUpdate();
        ps.close();

        return true;  
    }
    
}
