package vinid.vinhome.entities;
// Generated Dec 29, 2019 10:33:01 PM by Hibernate Tools 4.3.5.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PaymentProvider generated by hbm2java
 */
@Entity
@Table(name = "payment_provider", catalog = "vinid_home")
public class PaymentProvider implements java.io.Serializable {

	private Integer paymentProviderId;
	private Status status;
	private String name;
	private String code;
	private Date createdOn;
	private String createdBy;
	private Date modifiedOn;
	private String modifiedBy;
	private Set<PaymentHistory> paymentHistories = new HashSet<PaymentHistory>(0);

	public PaymentProvider() {
	}

	public PaymentProvider(Status status, String name, String code, Date createdOn, String createdBy, Date modifiedOn,
			String modifiedBy) {
		this.status = status;
		this.name = name;
		this.code = code;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.modifiedOn = modifiedOn;
		this.modifiedBy = modifiedBy;
	}

	public PaymentProvider(Status status, String name, String code, Date createdOn, String createdBy, Date modifiedOn,
			String modifiedBy, Set<PaymentHistory> paymentHistories) {
		this.status = status;
		this.name = name;
		this.code = code;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.modifiedOn = modifiedOn;
		this.modifiedBy = modifiedBy;
		this.paymentHistories = paymentHistories;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "payment_provider_id", unique = true, nullable = false)
	public Integer getPaymentProviderId() {
		return this.paymentProviderId;
	}

	public void setPaymentProviderId(Integer paymentProviderId) {
		this.paymentProviderId = paymentProviderId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status", nullable = false)
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "code", nullable = false, length = 100)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "created_on", nullable = false, length = 10)
	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name = "created_by", nullable = false, length = 100)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "modified_on", nullable = false, length = 10)
	public Date getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	@Column(name = "modified_by", nullable = false, length = 100)
	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "paymentProvider")
	public Set<PaymentHistory> getPaymentHistories() {
		return this.paymentHistories;
	}

	public void setPaymentHistories(Set<PaymentHistory> paymentHistories) {
		this.paymentHistories = paymentHistories;
	}

}