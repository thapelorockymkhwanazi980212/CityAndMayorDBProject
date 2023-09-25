
package cityapp;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.ac.tut.bl.CityManagerDB;
import za.ac.tut.entity.City;
import za.ac.tut.entity.Mayor;


/**
 *
 * @author Thapelo Mkhwanazi
 */
public class CityApp
{
    

    public static void main(String[] args) 
    {
        
        
       String dbURL = "jdbc:derby://localhost:1527/CityDB";
       String username = "app";
       String password = "123";
       
       String addCitySQL = "INSERT INTO CITY " + "VALUES(?, ?, ?, ?)";  //SQL that insert the unknown values in the table, id, name, population, mayor
       
       String addMayorSQL = "INSERT INTO MAYOR " + "VALUES(?, ?, ?)"; //sql to getadd mayor
       
        String getCitySQL = "SELECT City.id, City.name, City.population, Mayor.id AS mayor_id, Mayor.name AS mayor_name, Mayor.surname AS mayor_surname " +
         "FROM City " +
         "INNER JOIN Mayor ON Mayor.city_id = City.id " +
         "WHERE City.id =?";

       String getAllCitiesSQL = "SELECT City.id, City.name, City.population, Mayor.id AS mayor_id, Mayor.name AS mayor_name, Mayor.surname AS mayor_surname " +
        "FROM City " +
        "INNER JOIN Mayor ON Mayor.city_id = City.id ";
       
        String updateCitySQL = "UPDATE Mayor SET " +
        "Mayor.name =?, " +
        "Mayor.surname =? " +
        "WHERE Mayor.city_id =?";
        
        String deleteCitySQL = "DELETE FROM City " +
            "WHERE City.id =?";
        
            String deleteMayorSQL = "DELETE FROM Mayor " +
            "WHERE Mayor.city_id =?";
            
            int option, cityId;
            City city;
            List<City> cities;
            
            
        try 
        {
            CityManagerDB db = new CityManagerDB(dbURL, username, password);
            
            option = getOption();
            
            while(option != 6)
            {
                switch(option)
                {
                    case 1:
                        city = getCity();
                        db.add(city, addCitySQL, addMayorSQL);
                        
                        break;
                    case 2:
                      //getcity
                        cityId = getCityID();
                        city = db.get(cityId, getCitySQL);
                        
                        if(city != null)
                        {
                            displayCity(city);
                        }
                        else
                        {
                            System.out.println("There is no city with id" + cityId);
                        }
                        
                        break;
                        
                    case 3:
                        
                        cities = db.getAll(getAllCitiesSQL);
                        
                        if(cities != null)
                        {
                            displayAll(cities);
                        }
                        else
                        {
                            System.out.println("There are no cities in the database");
                        }
                        break;
                        
                    case 4:
                        city = getCityToUpdate();
                        db.update(city, updateCitySQL);
                        
                        break;
                        
                    case 5 :
                        cityId = getCityID();
                        db.delete(cityId, deleteCitySQL);
                        db.delete(cityId, deleteCitySQL);//deleteMayorSQL
                        break;
                        
                    default:
                        System.out.println(option + " is invalid");
                    
                }
                
                option = getOption();
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(CityApp.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    private static int getOption()
    {
        Scanner sc = new Scanner(System.in);
        
          System.out.print("\nPlease select one of the following options: " + "\n" +
         "--------------------------------------------" + "\n" +
         "1 - Add city" + "\n" +
         "2 - Get city" + "\n" +
         "3 - Get all cities in a country" + "\n" +
         "4 - Update city" + "\n" +
         "5 - Delete city" + "\n" +
         "6 - Exit" + "\n\n" +
         "Your option: ");
         int option = sc.nextInt();
         return option;
    }
    
    private static City getCity()
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Please enter city details" + "\n" + 
                "----------------------");  
        
        //city details
        System.out.println("Enter City ID:");
        int cityid = sc.nextInt();
        
        System.out.println("Enter city name: ");
        String cityname =  sc.next();
        
        System.out.println("Enter city population");
        int pop = sc.nextInt();
        
        //mayor details
        System.out.println("Please enter details of the city mayor" + "\n" + 
                "----------------------");
        
        System.out.println("Enter mayor ID: ");
        int mayorid =  sc.nextInt();
        
        System.out.println("Enter mayor name: ");
        String mayorname =  sc.next();
        
        System.out.println("Enter major surnname: ");
        String mayorsurname =  sc.next();
        
        Mayor mayor = new Mayor(mayorid, mayorname, mayorsurname);
        
        City city = new City(cityid, cityname, pop, mayor);
        
        return city;     
    }
    
    
    private static int  getCityID()
    {
       Scanner sc = new Scanner(System.in);
        System.out.println("Please enter city ID: ");
        int id = sc.nextInt();
        
        return id;
    }
    
    private static void displayCity(City city) 
    {
        System.out.println("\nCity details." + "\n" +
        "-------------");
        System.out.println(city);
    }
    
    private static void displayAll(List<City> cities) 
    {
        System.out.println("\nCities of a country" + "\n" +
        "---------------------");
        System.out.println(cities);
    }
    
    private static City getCityToUpdate() 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nPlease enter city information below." + "\n" +
        "------------------------------------");
        System.out.print("ID: ");
        Integer id = sc.nextInt();
        System.out.print("New Mayor name: ");
        String mayorName = sc.next();
        System.out.print("New Mayor surname: ");
        String mayorSurname = sc.next();

        Mayor mayor = new Mayor();
        mayor.setName(mayorName);
        mayor.setSurname(mayorSurname);

        City city = new City();
        city.setId(id);
        city.setMayor(mayor);

        return city;
 }

}
