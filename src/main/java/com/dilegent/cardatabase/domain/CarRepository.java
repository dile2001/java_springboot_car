package com.dilegent.cardatabase.domain;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
/*The <Car, Long> type arguments define that this is the repository for the Car entity class 
 * and the type of the ID field is Long.
 */
public interface CarRepository extends CrudRepository<Car,Long> {
	
    // Fetch cars by color
    List<Car> findByColor(String color);
    // Fetch cars by model year
    List<Car> findByModelYear(int modelYear);
    // Fetch cars by brand and model
    List<Car> findByBrandAndModel(String brand, String model);
    // Fetch cars by brand or color
    List<Car> findByBrandOrColor(String brand, String color);
    // Fetch cars by brand and sort by year
    List<Car> findByBrandOrderByModelYearAsc(String brand);
    
    // Fetch cars by brand using SQL
    @Query("select c from Car c where c.brand = ?1")
    List<Car> findByBrand(String brand);
    //If you use the @Query annotation and write SQL queries in your code,
    //your application might be less portable across different database systems.
}
