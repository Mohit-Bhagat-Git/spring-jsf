package gui.main.model.java;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import nocturnal.host.model.Address;
import nocturnal.host.model.Customer;

@ManagedBean
@SessionScoped
public class GuiOrder implements Serializable{

	private Long orderId;
	private String orderStatus;//searchField
	private Date orderDateTime;//searchField
	private Date orderCancelTime;//searchField
	private BigDecimal amountToPay;
	private Long customerId;//searchField
	private Date fromDate;//searchFiled
	private Date toDate;//searchField
	private Date orderCompletionTime;
	private String orderStatusReason;
	private Address orderAddress;
	private Customer customer;
	private boolean editable;
	private Set<GuiSubOrder> guiSubOrderSet = new HashSet<GuiSubOrder>();

	
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public Set<GuiSubOrder> getGuiSubOrderSet() {
		return guiSubOrderSet;
	}

	public void setGuiSubOrderSet(Set<GuiSubOrder> guiSubOrderSet) {
		this.guiSubOrderSet = guiSubOrderSet;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setOrderAddress(Address orderAddress) {
		this.orderAddress = orderAddress;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderStatusReason() {
		return orderStatusReason;
	}

	public void setOrderStatusReason(String orderStatusReason) {
		this.orderStatusReason = orderStatusReason;
	}

	public Date getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(Date orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	public Date getOrderCancelTime() {
		return orderCancelTime;
	}

	public void setOrderCancelTime(Date orderCancelTime) {
		this.orderCancelTime = orderCancelTime;
	}

	public Date getOrderCompletionTime() {
		return orderCompletionTime;
	}

	public void setOrderCompletionTime(Date orderCompletionTime) {
		this.orderCompletionTime = orderCompletionTime;
	}

	public BigDecimal getAmountToPay() {
		return amountToPay;
	}

	public void setAmountToPay(BigDecimal amountToPay) {
		this.amountToPay = amountToPay;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Address getOrderAddress() {
		return orderAddress;
	}

}
