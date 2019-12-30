package vinid.vinhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vinid.vinhome.entities.District;

import java.util.List;

public interface IDistrictRepository extends JpaRepository<District,Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM district" )
    List<District> getAllDistrict();

}
