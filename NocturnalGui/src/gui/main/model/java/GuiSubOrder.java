package gui.main.model.java;

import java.math.BigDecimal;
import java.util.Date;

import nocturnal.host.model.Product;

public class GuiSubOrder {
	
	private Long orderId;
	private String productName;
	private String subCanclDate;
	private Product product;
	private String orderStatus;
	private Date orderDateTime;
	private Date orderCompletionTime;
	private String subOrderStatusReason;
	private BigDecimal subOrderPrice;
	private BigDecimal offerPrice;
	private int prodQuantity;

	
	public Date getOrderCompletionTime() {
		return orderCompletionTime;
	}

	public void setOrderCompletionTime(Date orderCompletionTime) {
		this.orderCompletionTime = orderCompletionTime;
	}

	


	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName
	 * this is deprecated as it is not necessary to have prod name set when we store fully prod obj reference to this class.
	 */
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

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(Date orderDateTime) {
		this.orderDateTime = orderDateTime;
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
