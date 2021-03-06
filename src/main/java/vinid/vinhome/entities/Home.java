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
 * Home generated by hbm2java
 */
@Entity
@Table(name = "home", catalog = "vinid_home")
public class Home implements java.io.Serializable {

	private Long homeId;
	private Status status;
	private String content;
	private String imageUrl;
	private double price;
	private Date createdOn;
	private String createdBy;
	private Date modifiedOn;
	private String modifiedBy;
	private Set<OrderInfo> orderInfos = new HashSet<OrderInfo>(0);
	private Set<AdressHome> adressHomes = new HashSet<AdressHome>(0);
	private Set<AcreageHome> acreageHomes = new HashSet<AcreageHome>(0);
	private Set<HomeWorktime> homeWorktimes = new HashSet<HomeWorktime>(0);
	private Set<Order> orders = new HashSet<Order>(0);
	private Set<UserHome> userHomes = new HashSet<UserHome>(0);

	public Home() {
	}

	public Home(Status status, String content, String imageUrl, double price, Date createdOn, String createdBy,
			Date modifiedOn, String modifiedBy) {
		this.status = status;
		this.content = content;
		this.imageUrl = imageUrl;
		this.price = price;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.modifiedOn = modifiedOn;
		this.modifiedBy = modifiedBy;
	}

	public Home(Status status, String content, String imageUrl, double price, Date createdOn, String createdBy,
			Date modifiedOn, String modifiedBy, Set<OrderInfo> orderInfos, Set<AdressHome> adressHomes,
			Set<AcreageHome> acreageHomes, Set<HomeWorktime> homeWorktimes, Set<Order> orders,
			Set<UserHome> userHomes) {
		this.status = status;
		this.content = content;
		this.imageUrl = imageUrl;
		this.price = price;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.modifiedOn = modifiedOn;
		this.modifiedBy = modifiedBy;
		this.orderInfos = orderInfos;
		this.adressHomes = adressHomes;
		this.acreageHomes = acreageHomes;
		this.homeWorktimes = homeWorktimes;
		this.orders = orders;
		this.userHomes = userHomes;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "home_id", unique = true, nullable = false)
	public Long getHomeId() {
		return this.homeId;
	}

	public void setHomeId(Long homeId) {
		this.homeId = homeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status", nullable = false)
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Column(name = "content", nullable = false, length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "image_url", nullable = false)
	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Column(name = "price", nullable = false, precision = 22, scale = 0)
	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "home")
	public Set<OrderInfo> getOrderInfos() {
		return this.orderInfos;
	}

	public void setOrderInfos(Set<OrderInfo> orderInfos) {
		this.orderInfos = orderInfos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "home")
	public Set<AdressHome> getAdressHomes() {
		return this.adressHomes;
	}

	public void setAdressHomes(Set<AdressHome> adressHomes) {
		this.adressHomes = adressHomes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "home")
	public Set<AcreageHome> getAcreageHomes() {
		return this.acreageHomes;
	}

	public void setAcreageHomes(Set<AcreageHome> acreageHomes) {
		this.acreageHomes = acreageHomes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "home")
	public Set<HomeWorktime> getHomeWorktimes() {
		return this.homeWorktimes;
	}

	public void setHomeWorktimes(Set<HomeWorktime> homeWorktimes) {
		this.homeWorktimes = homeWorktimes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "home")
	public Set<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "home")
	public Set<UserHome> getUserHomes() {
		return this.userHomes;
	}

	public void setUserHomes(Set<UserHome> userHomes) {
		this.userHomes = userHomes;
	}

}
