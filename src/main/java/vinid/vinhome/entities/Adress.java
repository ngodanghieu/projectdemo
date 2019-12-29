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
 * Adress generated by hbm2java
 */
@Entity
@Table(name = "adress", catalog = "vinid_home")
public class Adress implements java.io.Serializable {

	private Integer adressId;
	private District district;
	private long homeId;
	private long latitude;
	private long longtitude;
	private String contentDetail;

	public Adress() {
	}

	public Adress(District district, long homeId, long latitude, long longtitude, String contentDetail) {
		this.district = district;
		this.homeId = homeId;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.contentDetail = contentDetail;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "adress_id", unique = true, nullable = false)
	public Integer getAdressId() {
		return this.adressId;
	}

	public void setAdressId(Integer adressId) {
		this.adressId = adressId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id", nullable = false)
	public District getDistrict() {
		return this.district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	@Column(name = "home_id", nullable = false)
	public long getHomeId() {
		return this.homeId;
	}

	public void setHomeId(long homeId) {
		this.homeId = homeId;
	}

	@Column(name = "latitude", nullable = false, precision = 10, scale = 0)
	public long getLatitude() {
		return this.latitude;
	}

	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}

	@Column(name = "longtitude", nullable = false, precision = 10, scale = 0)
	public long getLongtitude() {
		return this.longtitude;
	}

	public void setLongtitude(long longtitude) {
		this.longtitude = longtitude;
	}

	@Column(name = "content_detail", nullable = false)
	public String getContentDetail() {
		return this.contentDetail;
	}

	public void setContentDetail(String contentDetail) {
		this.contentDetail = contentDetail;
	}

}
