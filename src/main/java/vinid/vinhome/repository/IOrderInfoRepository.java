package vinid.vinhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vinid.vinhome.entities.OrderInfo;

public interface IOrderInfoRepository extends JpaRepository<OrderInfo,Long> {
}
