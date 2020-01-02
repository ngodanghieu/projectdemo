package vinid.vinhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vinid.vinhome.entities.AcreageHome;

public interface IAcreageHomeRepository extends JpaRepository<AcreageHome,Long> {
    @Query(nativeQuery = true,
        value = "SELECT * FROM acreage_home WHERE home_id = :idhome "
    )
    AcreageHome getByIdHome(@Param("idhome") Long idHome);
}
