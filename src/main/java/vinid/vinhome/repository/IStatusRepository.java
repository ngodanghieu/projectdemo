package vinid.vinhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vinid.vinhome.entities.Status;

public interface IStatusRepository extends JpaRepository<Status,Long> {
    Status findByStatusCode(String code);
}
