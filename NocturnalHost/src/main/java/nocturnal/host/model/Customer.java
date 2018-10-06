package nocturnal.host.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

@Entity
@Table(name= "CUSTOMER")
@NamedQueries({
		@NamedQuery(name = "findCustomer", query = "SELECT e " + "FROM Customer e " + "WHERE e.mobile = :mob AND "
				+ "      e.custFirstName = :fName AND " + "e.custLastName = :lName"),
		@NamedQuery(name = "loginCustomer", query = "select e from Customer e where (e.mobile=:mob or e.customerId=:cId) and e.password=:pass") })
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cust_seq")
	@SequenceGenerator(name = "cust_seq", initialValue = 10000000, allocationSize = 1, sequenceName = "cust_auto_seq")
	@Column(name = "CUSTOMER_ID", nullable = false)
	private Long customerId;

	@Column(name = "CUST_MOBILE", nullable = false)
	private String mobile;

	@OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "cust_FK", referencedColumnName = "CUSTOMER_ID")
	private Set<Address> addressSet = new HashSet<Address>();
	
	@OneToMany(cascade={CascadeType.REMOVE, CascadeType.REFRESH}, fetch=FetchType.EAGER, mappedBy="customer")
	//@JoinColumn(name="order_FK", referencedColumnName="CUSTOMER_ID")
	private Set<Order> orderSet = new HashSet<Order>();

	@Column(name = "CUST_PASS")
	private String password="";

	@Column(name = "E_CASH", nullable = false)
	private int eCash = 0;

	@Column(name = "CUST_EMAIL_ID")
	private String emailId="";

	@Column(name = "CUST_FIRST_NAME", nullable = false)
	private String custFirstName;

	@Column(name = "CUST_LAST_NAME")
	private String custLastName="";

	
	public Set<Address> getAddressSet() {
		return addressSet;
	}

	public void setAddressSet(Set<Address> addressSet) {
		this.addressSet = addressSet;
	}

	public Set<Order> getOrderSet() {
		return orderSet;
	}

	public void setOrderSet(Set<Order> orderSet) {
		this.orderSet = orderSet;
	}

	public int geteCash() {
		return eCash;
	}

	public void seteCash(int eCash) {
		this.eCash = eCash;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getECash() {
		return eCash;
	}

	public void setECash(int eCash) {
		this.eCash = eCash;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId.toLowerCase();
	}

	public String getCustFirstName() {
		return custFirstName;
	}

	public void setCustFirstName(String custFirstName) {
		this.custFirstName = custFirstName.toUpperCase();
	}

	public String getCustLastName() {
		return custLastName;
	}

	public void setCustLastName(String custLastName) {
		this.custLastName = custLastName.toUpperCase();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((custFirstName == null) ? 0 : custFirstName.hashCode());
		result = prime * result + ((custLastName == null) ? 0 : custLastName.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
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
		Customer other = (Customer) obj;
		if (custFirstName == null) {
			if (other.custFirstName != null)
				return false;
		} else if (!custFirstName.equals(other.custFirstName))
			return false;
		if (custLastName == null) {
			if (other.custLastName != null)
				return false;
		} else if (!custLastName.equals(other.custLastName))
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		return true;
	}

}
