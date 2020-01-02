package vinid.vinhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vinid.vinhome.entities.AdressHome;

public interface IAdressHome extends JpaRepository<AdressHome,Long> {

    @Query(nativeQuery = true,  value = "SELECT * FROM adress_home WHERE home_id = :homeid")
    AdressHome findAdressHomeByHome(@Param("homeid") Long id);
}
