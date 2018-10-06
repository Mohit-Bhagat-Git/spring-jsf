package nocturnal.host.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ORDERS")
public class Order {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="order_seq")
	@SequenceGenerator(name = "order_seq", initialValue=1000000000, allocationSize=1, sequenceName="order_auto_seq" )
	@Column(name="orderId", nullable=false)
	private Long orderId;
	
	@Column(name="order_status",nullable=false)
	private String orderStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="order_date_time", nullable=false)
	private Date orderDateTime = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="order_cancelled_time")
	private Date orderCancelTime;
	
	@Column(name="order_total_amount",nullable=false)
	private BigDecimal amountToPay;

	/*//@ManyToOne
	@Column(name="customer_id", nullable=false)
	private String customer_id;*/
	
	@Column(name="order_completion_time")
	private Date orderCompletionTime;
	
	@Column(name="order_status_reason")
	private String orderStatusReason;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="address_FK", nullable=false)
	private Address orderAddress;

	@ManyToOne(cascade=CascadeType.ALL)
	//@JoinColumn(name="cust_FK", nullable=false)
	private Customer customer;
	
	@OneToMany(targetEntity=SubOrder.class, cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="orderFK" , referencedColumnName="orderId")
	private Set<SubOrder> subOrderSet = new HashSet<SubOrder>();
	 
	public Set<SubOrder> getSubOrderSet() {
		return subOrderSet;
	}

	public void setSubOrderSet(Set<SubOrder> subOrderSet) {
		this.subOrderSet = subOrderSet;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((orderDateTime == null) ? 0 : orderDateTime.hashCode());
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
		Order other = (Order) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (orderDateTime == null) {
			if (other.orderDateTime != null)
				return false;
		} else if (!orderDateTime.equals(other.orderDateTime))
			return false;
		if (orderStatus == null) {
			if (other.orderStatus != null)
				return false;
		} else if (!orderStatus.equals(other.orderStatus))
			return false;
		return true;
	}

	

}
