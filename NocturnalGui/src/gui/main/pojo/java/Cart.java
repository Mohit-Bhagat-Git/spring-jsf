package gui.main.pojo.java;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import gui.main.model.java.GuiProduct;

@ManagedBean
@SessionScoped
public class Cart implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5144955662986822246L;
	private List<GuiProduct> cartProdList = new LinkedList<GuiProduct>();
	private BigDecimal cartTotalPrice;
	
	public List<GuiProduct> getCartProdList() {
		return cartProdList;
	}

	public void setCartProdList(List<GuiProduct> cartProdList) {
		this.cartProdList = cartProdList;
	}
	
	@PostConstruct
	public void cartTotal(){
		
		
	}

	public BigDecimal getCartTotalPrice() {
		cartTotalPrice = new BigDecimal(0);
		for(GuiProduct obj: cartProdList){
			int prodQuantity = obj.getProductQuantity();
			BigDecimal prodPrice = obj.getProductPrice();
			cartTotalPrice = cartTotalPrice.add(prodPrice.multiply(new BigDecimal(prodQuantity)));
		}
		return cartTotalPrice;
	}

	public void setCartTotalPrice(BigDecimal cartTotalPrice) {
		this.cartTotalPrice = cartTotalPrice;
	}
	
	
	

}
