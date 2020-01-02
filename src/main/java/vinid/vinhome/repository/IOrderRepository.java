package vinid.vinhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vinid.vinhome.entities.Order;

public interface IOrderRepository extends JpaRepository<Order,Long> {

}
