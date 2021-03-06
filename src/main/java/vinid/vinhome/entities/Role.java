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
 * Role generated by hbm2java
 */
@Entity
@Table(name = "role", catalog = "vinid_home")
public class Role implements java.io.Serializable {

	private Integer roleId;
	private Status status;
	private String roleName;
	private String roleCode;
	private Date roleCreatedOn;
	private String roleCreatedBy;
	private Date roleModifiedOn;
	private String roleModifiedBy;
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);

	public Role() {
	}

	public Role(Status status, String roleName, String roleCode, Date roleCreatedOn, String roleCreatedBy,
			Date roleModifiedOn, String roleModifiedBy) {
		this.status = status;
		this.roleName = roleName;
		this.roleCode = roleCode;
		this.roleCreatedOn = roleCreatedOn;
		this.roleCreatedBy = roleCreatedBy;
		this.roleModifiedOn = roleModifiedOn;
		this.roleModifiedBy = roleModifiedBy;
	}

	public Role(Status status, String roleName, String roleCode, Date roleCreatedOn, String roleCreatedBy,
			Date roleModifiedOn, String roleModifiedBy, Set<UserRole> userRoles) {
		this.status = status;
		this.roleName = roleName;
		this.roleCode = roleCode;
		this.roleCreatedOn = roleCreatedOn;
		this.roleCreatedBy = roleCreatedBy;
		this.roleModifiedOn = roleModifiedOn;
		this.roleModifiedBy = roleModifiedBy;
		this.userRoles = userRoles;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "role_id", unique = true, nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_status", nullable = false)
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Column(name = "role_name", nullable = false, length = 200)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "role_code", nullable = false, length = 100)
	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "role_created_on", nullable = false, length = 10)
	public Date getRoleCreatedOn() {
		return this.roleCreatedOn;
	}

	public void setRoleCreatedOn(Date roleCreatedOn) {
		this.roleCreatedOn = roleCreatedOn;
	}

	@Column(name = "role_created_by", nullable = false, length = 100)
	public String getRoleCreatedBy() {
		return this.roleCreatedBy;
	}

	public void setRoleCreatedBy(String roleCreatedBy) {
		this.roleCreatedBy = roleCreatedBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "role_modified_on", nullable = false, length = 10)
	public Date getRoleModifiedOn() {
		return this.roleModifiedOn;
	}

	public void setRoleModifiedOn(Date roleModifiedOn) {
		this.roleModifiedOn = roleModifiedOn;
	}

	@Column(name = "role_modified_by", nullable = false, length = 100)
	public String getRoleModifiedBy() {
		return this.roleModifiedBy;
	}

	public void setRoleModifiedBy(String roleModifiedBy) {
		this.roleModifiedBy = roleModifiedBy;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

}
