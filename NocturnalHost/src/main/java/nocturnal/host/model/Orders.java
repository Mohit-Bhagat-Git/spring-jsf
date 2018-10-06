package nocturnal.host.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ORDERS")
public class Orders {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="order_seq")
	@SequenceGenerator(name = "order_seq", initialValue=1000000000, allocationSize=1, sequenceName="order_auto_seq" )
	@Column(name="order_id", nullable=false)
	private Long OrderId;
	
	@Id
	@Column(name="product_id", nullable=false)
	private String productId;
	
	@Column(name="customer_id", nullable=false)
	private String customerId;
	
	@Column(name="order_date_time", nullable=false)
	private Date orderDateTime;
	
	@Column(name="order_completion_time", nullable=false)
	private Date orderCompletionTime;
	
	@Column(name="order_address", nullable=false)
	private String orderAddress;

	@Column(name="order_cancel_time")
	private String orderCancelTime;
	
	public Long getOrderId() {
		return OrderId;
	}

	public void setOrderId(Long orderId) {
		OrderId = orderId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Date getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(Date orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	public Date getOrderCompletionTime() {
		return orderCompletionTime;
	}

	public void setOrderCompletionTime(Date orderCompletionTime) {
		this.orderCompletionTime = orderCompletionTime;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}
	
	

}
