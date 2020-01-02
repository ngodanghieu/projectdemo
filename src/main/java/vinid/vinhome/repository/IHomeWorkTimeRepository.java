package vinid.vinhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vinid.vinhome.entities.HomeWorktime;

import java.util.List;

public interface IHomeWorkTimeRepository extends JpaRepository<HomeWorktime,Long> {

    @Query(nativeQuery = true,value = "SELECT FROM home_worktime home_id = :idhome")
    List<HomeWorktime> getAllByIdHome(@Param("idhome") Long id);


}
