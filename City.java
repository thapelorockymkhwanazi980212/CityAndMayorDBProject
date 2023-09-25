
package za.ac.tut.entity;

/**
 *
 * @author Thapelo Mkhwanazi
 */
public class City
{
    private Integer id;
    private String name;
    private Integer population;
    private Mayor mayor;

    public City() 
    {
        
    }

    public City(Integer id, String name, Integer population, Mayor mayor) 
    {
        this.id = id;
        this.name = name;
        this.population = population;
        this.mayor = mayor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Mayor getMayor() {
        return mayor;
    }

    public void setMayor(Mayor mayor) {
        this.mayor = mayor;
    }

    @Override
    public String toString() {
        return "City{" + "id=" + id + ", name=" + name + ", population=" + population + ", mayor=" + mayor + '}';
    }
    
    
}
