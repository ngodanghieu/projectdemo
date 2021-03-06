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
 * PaymentHistory generated by hbm2java
 */
@Entity
@Table(name = "payment_history", catalog = "vinid_home")
public class PaymentHistory implements java.io.Serializable {

	private Integer paymentHistoryId;
	private PaymentProvider paymentProvider;
	private int userId;
	private long homeId;

	public PaymentHistory() {
	}

	public PaymentHistory(PaymentProvider paymentProvider, int userId, long homeId) {
		this.paymentProvider = paymentProvider;
		this.userId = userId;
		this.homeId = homeId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "payment_history_id", unique = true, nullable = false)
	public Integer getPaymentHistoryId() {
		return this.paymentHistoryId;
	}

	public void setPaymentHistoryId(Integer paymentHistoryId) {
		this.paymentHistoryId = paymentHistoryId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_provider_id", nullable = false)
	public PaymentProvider getPaymentProvider() {
		return this.paymentProvider;
	}

	public void setPaymentProvider(PaymentProvider paymentProvider) {
		this.paymentProvider = paymentProvider;
	}

	@Column(name = "user_id", nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "home_id", nullable = false)
	public long getHomeId() {
		return this.homeId;
	}

	public void setHomeId(long homeId) {
		this.homeId = homeId;
	}

}
