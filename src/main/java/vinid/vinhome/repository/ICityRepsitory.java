package vinid.vinhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vinid.vinhome.entities.City;

import java.util.List;

public interface ICityRepsitory extends JpaRepository<City,Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM city")
    List<City> getAll();

}
