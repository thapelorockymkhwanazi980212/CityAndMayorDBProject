
package za.ac.tut.bl;

import java.sql.SQLException;
import java.util.List;
import za.ac.tut.entity.City;

/**
 *
 * @author Thapelo Mkhwanazi
 */
public interface CityManagerInterface 
{

        public void add(City city, String sqlAddCity, String sqlAddMayor) throws SQLException;
        public City get(Integer id, String sql) throws SQLException;
        public List<City> getAll(String sql) throws SQLException;
        public boolean update(City city, String sql) throws SQLException;
        public boolean delete(Integer cityID, String sql) throws SQLException;
    
}
