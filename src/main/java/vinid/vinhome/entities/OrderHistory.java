package vinid.vinhome.entities;
// Generated Dec 29, 2019 10:33:01 PM by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * OrderHistory generated by hbm2java
 */
@Entity
@Table(name = "order_history", catalog = "vinid_home")
public class OrderHistory implements java.io.Serializable {

	private Integer orderHistoryId;
	private User user;
	private String orderRequest;

	public OrderHistory() {
	}

	public OrderHistory(User user, String orderRequest) {
		this.user = user;
		this.orderRequest = orderRequest;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "order_history_id", unique = true, nullable = false)
	public Integer getOrderHistoryId() {
		return this.orderHistoryId;
	}

	public void setOrderHistoryId(Integer orderHistoryId) {
		this.orderHistoryId = orderHistoryId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "order_request", nullable = false, length = 65535)
	public String getOrderRequest() {
		return this.orderRequest;
	}

	public void setOrderRequest(String orderRequest) {
		this.orderRequest = orderRequest;
	}

}
