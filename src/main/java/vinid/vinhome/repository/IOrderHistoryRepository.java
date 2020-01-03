package vinid.vinhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vinid.vinhome.entities.OrderHistory;

public interface IOrderHistoryRepository extends JpaRepository<OrderHistory,Long> {
}
