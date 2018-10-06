package nocturnal.host.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*@Entity
@Table(name= "CUSTOMER")
@NamedQueries({
		@NamedQuery(name = "findCustomer", query = "SELECT e " + "FROM Customer e " + "WHERE e.mobile = :mob AND "
				+ "      e.custFirstName = :fName AND " + "e.custLastName = :lName"),

		@NamedQuery(name = "loginCustomer", query = "select e from Customer e where (e.mobile=:mob or e.customerId=:cId) and e.password=:pass") })
*/
@Embeddable
public class CustomerPk implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -8218810195695195957L;

	@Column(name = "CUST_MOBILE", nullable = false)
	private String mobile;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
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
		CustomerPk other = (CustomerPk) obj;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		return true;
	}

	

	

	
}
