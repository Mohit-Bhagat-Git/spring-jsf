package nocturnal.host.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * @author Mohit
 * This class have total of 9 fields
 */
@Entity
@Table(name = "ADDRESS")
public class Address {
	
	/**
	 * This field is not null and auto generated its value is started from 1
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="address_seq")
	@SequenceGenerator(name = "address_seq", initialValue=1, allocationSize=1, sequenceName="address_auto_seq" )
	@Column(name="ADDRESS_ID", nullable=false)
	public Long addressId;
	
	/**
	 * This field is not null 
	 */
	@Column(name="ADDRESS_HNO", nullable=false)
	public String houseNumber;
	
	/**
	 * This field is not null
	 */
	@Column(name="ADDRESS_COMPLETE", nullable=false)
	public String completeAddress;
	
	@Column(name="ADDRESS_STREET")
	public String street="";
	
	@Column(name="ADDRESS_LANDMARK")
	public String landMark="";
	
	@Column(name="ADDRESS_LOCALITY")
	public String locality="";
	
	/**
	 * This field is not null
	 */
	@Column(name="ADDRESS_CITY", nullable=false)
	public String city;
	
	/**
	 * this field is not Null
	 */
	@Column(name="ADDRESS_STATE", nullable=false)
	public String state;
	
	/**
	 * Not null
	 */
	@Column(name="ADDRESS_PINCODE", nullable=false)
	public Long pinCode;
	
	/*@ManyToOne(targetEntity=Customer.class, cascade=CascadeType.ALL)
	@JoinColumn(name="cust_address_id", referencedColumnName="ADDRESS_ID")*/
	//public Customer customer;

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber.toUpperCase();
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street.toUpperCase();
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark.toUpperCase();
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality.toUpperCase();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city.toUpperCase();
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state.toUpperCase();
	}

	public Long getPinCode() {
		return pinCode;
	}

	public void setPinCode(Long pinCode) {
		this.pinCode = pinCode;
	}

	
	public String getCompleteAddress() {
		return completeAddress;
	}

	public void setCompleteAddress(String completeAddress) {
		this.completeAddress = completeAddress.toUpperCase();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((houseNumber == null) ? 0 : houseNumber.hashCode());
		result = prime * result + ((locality == null) ? 0 : locality.hashCode());
		result = prime * result + ((pinCode == null) ? 0 : pinCode.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (houseNumber == null) {
			if (other.houseNumber != null)
				return false;
		} else if (!houseNumber.equals(other.houseNumber))
			return false;
		if (locality == null) {
			if (other.locality != null)
				return false;
		} else if (!locality.equals(other.locality))
			return false;
		if (pinCode == null) {
			if (other.pinCode != null)
				return false;
		} else if (!pinCode.equals(other.pinCode))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

	
}
