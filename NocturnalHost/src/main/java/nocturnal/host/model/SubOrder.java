package nocturnal.host.model;

import java.math.BigDecimal;
import java.util.Date;

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

@Entity
@Table(name="SUB_ORDERS")
public class SubOrder {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sub_order_seq")
	@SequenceGenerator(name = "sub_order_seq", initialValue=1000000000, allocationSize=1, sequenceName="sub_order_auto_seq" )
	@Column(name="sub_order_id", nullable=false)
	private Long subOrderId;
	
	
	@Column(name="product_name" , length=50)
	private String productName;
	
	@Column(name="cancel_date")
	public String subCanclDate;
	
	@ManyToOne(targetEntity=Product.class, cascade=CascadeType.ALL)
	@JoinColumn(name="product_FK", nullable=false)
	private Product product;
	
	@Column(name="sub_order_status", nullable=false)
	private String subOrderStatus;
	
	@Column(name="sub_order_completion_time")
	private Date orderCompletionTime;
	
	@Column(name="sub_order_status_reason")
	private String subOrderStatusReason;
	
	
	@Column(name="sub_order_price")
	private BigDecimal subOrderPrice;
	
	@Column(name="offer_price")
	private BigDecimal offerPrice;
	
	@Column(name="items_ordered")
	private int prodQuantity;

	
	public Date getOrderCompletionTime() {
		return orderCompletionTime;
	}

	public void setOrderCompletionTime(Date orderCompletionTime) {
		this.orderCompletionTime = orderCompletionTime;
	}

	public Long getSubOrderId() {
		return subOrderId;
	}

	public void setSubOrderId(Long subOrderId) {
		this.subOrderId = subOrderId;
	}

	public String getProductName() {
		return productName;
	}

	@Deprecated
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSubCanclDate() {
		return subCanclDate;
	}

	public void setSubCanclDate(String subCanclDate) {
		this.subCanclDate = subCanclDate;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getSubOrderStatus() {
		return subOrderStatus;
	}

	public void setSubOrderStatus(String subOrderStatus) {
		this.subOrderStatus = subOrderStatus;
	}

	public String getSubOrderStatusReason() {
		return subOrderStatusReason;
	}

	public void setSubOrderStatusReason(String subOrderStatusReason) {
		this.subOrderStatusReason = subOrderStatusReason;
	}

	public BigDecimal getSubOrderPrice() {
		return subOrderPrice;
	}

	public void setSubOrderPrice(BigDecimal subOrderPrice) {
		this.subOrderPrice = subOrderPrice;
	}

	public BigDecimal getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(BigDecimal offerPrice) {
		this.offerPrice = offerPrice;
	}

	public int getProdQuantity() {
		return prodQuantity;
	}

	public void setProdQuantity(int prodQuantity) {
		this.prodQuantity = prodQuantity;
	}
	
	
	

}
