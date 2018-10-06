package nocturnal.host.model;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name= "ADMIN")
@NamedQueries({
	@NamedQuery(name = "findAdmin", query = "SELECT e FROM Admin e WHERE e.userName = :user"),		
	@NamedQuery(name = "loginAdmin", query = "select e from Admin e where e.userName=:user and e.password=:pass") })
public class Admin {
	
	
	/**For security reasons Admin id must not be generated from the code
	 * One must provide this id if admin has to be updated.
	 */
	@Id
	@Column(name="admin_id")
	private int adminId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="role")
	private String role;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int i) {
		this.adminId = i;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + adminId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		if (adminId != other.adminId)
			return false;
		return true;
	}
	
	
	

}
