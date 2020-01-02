package vinid.vinhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vinid.vinhome.entities.Acreage;
import vinid.vinhome.entities.AcreageHome;

public interface IAcreage extends JpaRepository<Acreage,Long> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM acreage INNER JOIN  acreage_home ON  acreage.acreage_id = acreage_home.acreage_id  WHERE acreage_home.home_id = :idhome "
    )
    Acreage getByIdHome(@Param("idhome") Long idHome);
}
