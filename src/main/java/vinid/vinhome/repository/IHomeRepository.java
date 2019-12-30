package vinid.vinhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vinid.vinhome.entities.Home;

import java.util.List;

public interface IHomeRepository extends JpaRepository<Home,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM home")
    List<Home> getAllHome();
}
