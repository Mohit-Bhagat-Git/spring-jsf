package gui.main.model.java;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import gui.main.pojo.java.Cart;

@ManagedBean
@SessionScoped
public class CustomerDetails {
	
	@ManagedProperty(value = "#{cart}")
	private Cart cart;
	
	private Long custID;
	private String emailId;
	private String password;
	private String customerFirstName;
	private String customerLastName;
	private CustomerAddress deliveryAddress;
	private Set<CustomerAddress> addressSet = new HashSet<CustomerAddress>();
	private String mobileNumber;
	private String ipLocation;
	private int eCash;
	private volatile boolean isLoggedIn=false;
	
	
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public boolean getIsLoggedIn() {
		return isLoggedIn;
	}
	public void setIsLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	public Set<CustomerAddress> getAddressSet() {
		return addressSet;
	}
	public void setAddressSet(Set<CustomerAddress> addressSet) {
		this.addressSet = addressSet;
	}
	public int geteCash() {
		return eCash;
	}
	public void seteCash(int eCash) {
		this.eCash = eCash;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCustomerFirstName() {
		return customerFirstName;
	}
	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}
	public String getCustomerLastName() {
		return customerLastName;
	}
	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}
	public CustomerAddress getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(CustomerAddress deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getIpLocation() {
		return ipLocation;
	}
	public void setIpLocation(String ipLocation) {
		this.ipLocation = ipLocation;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Long getCustID() {
		return custID;
	}
	public void setCustID(Long custID) {
		this.custID = custID;
	}

	public void onRowSelect(SelectEvent event) {
		
		this.deliveryAddress=(CustomerAddress) event.getObject();
        FacesMessage msg = new FacesMessage("Row Selected", ((CustomerAddress) event.getObject()).getAddressId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
