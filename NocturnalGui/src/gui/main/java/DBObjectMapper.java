package gui.main.java;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gui.admin.java.AdminUser;
import gui.main.model.java.CustomerAddress;
import gui.main.model.java.CustomerDetails;
import gui.main.model.java.GuiOrder;
import gui.main.model.java.GuiProduct;
import gui.main.model.java.GuiSubOrder;
import nocturnal.host.model.Address;
import nocturnal.host.model.Admin;
import nocturnal.host.model.Customer;
import nocturnal.host.model.Order;
import nocturnal.host.model.Product;
import nocturnal.host.model.SubOrder;

public class DBObjectMapper {

	private static final Logger logger = LogManager.getLogger(DBObjectMapper.class.getName());

	public Product guiToHost_product(GuiProduct prod) {

		Product hostProd = new Product();
		hostProd.setProductBrand(prod.getProductBrand().toLowerCase());
		hostProd.setProductCategory(prod.getProductCategory().toLowerCase());
		hostProd.setProductId(prod.getProductId());
		hostProd.setProductSize(prod.getProductSize());
		hostProd.setProductPrice(prod.getProductPrice());
		hostProd.setImageName(prod.getImageName());
		hostProd.setProductName(prod.getProductName());
		hostProd.setProductAvailability(prod.getProductQuantity());

		return hostProd;

	}

	public Customer guiToHost_customer(CustomerDetails cust) {
		Customer hostCust = new Customer();
		if (cust.getPassword()!=null) {//is adress is empty then surely for login request.
			//customer can give cust id as well as mobile number for login.
			if(cust.getCustID()!=null){
				hostCust.setCustomerId(Long.valueOf(cust.getCustID()));
			}
			else{
				hostCust.setMobile(cust.getMobileNumber());
			}
			hostCust.setPassword(cust.getPassword());
			
		} else {
			
			hostCust.setCustFirstName(cust.getCustomerFirstName());
			hostCust.setCustLastName(cust.getCustomerLastName());
			hostCust.setEmailId(cust.getEmailId());

			hostCust.setECash(cust.geteCash());

			for (CustomerAddress addr : cust.getAddressSet()) {
				// Adding all addresses provided by customer.
				Address hostAddr = guiToHost_address(addr);
				hostCust.getAddressSet().add(hostAddr);
			}
		}
		
			

		return hostCust;

	}

	public GuiProduct hostToGui_product(Product prod) {

		GuiProduct guiProd = new GuiProduct();
		guiProd.setProductBrand(prod.getProductBrand());
		guiProd.setProductCategory(prod.getProductCategory());
		guiProd.setProductId(prod.getProductId());
		guiProd.setProductSize((int) (long) (prod.getProductSize()));
		guiProd.setProductPrice(prod.getProductPrice());
		guiProd.setImageName(prod.getImageName());
		guiProd.setProductName(prod.getProductName());
		// guiProd.setProductQuantity(prod.getProductQuantity());

		return guiProd;

	}

	public Address guiToHost_address(CustomerAddress fromAddress) {

		try {
			Address toAddress = new Address();
			if(fromAddress.getAddressId()!=null){
				toAddress.setAddressId(fromAddress.addressId);
			}
			
			toAddress.setCity(fromAddress.getCity());
			toAddress.setState(fromAddress.getState());
			toAddress.setPinCode(fromAddress.getPinCode());
			toAddress.setLocality(fromAddress.getLocality());
			toAddress.setLandMark(fromAddress.getLandMark());
			toAddress.setCompleteAddress(fromAddress.getCompleteAddress());
			toAddress.setHouseNumber(fromAddress.getHouseNumber());
			return toAddress;
		} catch (Exception e) {
			logger.error("Exception occured while mapping {}", e);
		}

		return null;
	}
	
	public CustomerAddress hostToGui_address(Address fromAddress) {

		try {
			CustomerAddress toAddress = new CustomerAddress();
			toAddress.setAddressId(fromAddress.getAddressId());
			toAddress.setCity(fromAddress.getCity());
			toAddress.setState(fromAddress.getState());
			toAddress.setPinCode(fromAddress.getPinCode());
			toAddress.setLocality(fromAddress.getLocality());
			toAddress.setLandMark(fromAddress.getLandMark());
			toAddress.setCompleteAddress(fromAddress.getCompleteAddress());
			toAddress.setHouseNumber(fromAddress.getHouseNumber());
			return toAddress;
		} catch (Exception e) {
			logger.error("Exception occured while mapping {}", e);
		}

		return null;
	}

	public CustomerDetails hostToGui_customer(Customer from, CustomerDetails to) {
		if(to!=null){
			to = new CustomerDetails();
		}
		
		for(Address obj: from.getAddressSet()){
			CustomerAddress address = hostToGui_address(obj);
			to.getAddressSet().add(address);
		}
		to.setCustID(from.getCustomerId());
		to.setCustomerFirstName(from.getCustFirstName());
		to.setCustomerLastName(from.getCustLastName());
		to.setEmailId(from.getEmailId());
		to.setMobileNumber(from.getMobile());
		to.seteCash(from.getECash());
		to.setPassword(from.getPassword());
		
		return to;
	}

	public GuiOrder hostToGui_Order(Order from) {
		GuiOrder to = new GuiOrder();
		to.setOrderId(from.getOrderId());
		to.setAmountToPay(from.getAmountToPay());
		to.setCustomerId(from.getCustomer().getCustomerId());
		to.setOrderStatus(from.getOrderStatus());
		to.setOrderStatusReason(from.getOrderStatusReason());
		to.setOrderDateTime(from.getOrderDateTime());
		for(SubOrder host : from.getSubOrderSet()){
			to.getGuiSubOrderSet().add((hostToGui_SubOrder(host)));
		}
		
		
		
		return to;
	}

	public GuiSubOrder hostToGui_SubOrder(SubOrder from) {
		GuiSubOrder to = new GuiSubOrder();
		to.setOfferPrice(from.getOfferPrice());
		to.setOrderCompletionTime(from.getOrderCompletionTime());
		to.setProdQuantity(from.getProdQuantity());
		to.setProductName(from.getProduct().getProductName());
		to.setSubCanclDate(from.subCanclDate);
		to.setSubOrderPrice(from.getSubOrderPrice());
		to.setOrderId(from.getSubOrderId());
		to.setOrderStatus(from.getSubOrderStatus());
		to.setSubOrderStatusReason(from.getSubOrderStatusReason());
		return to;
	}

	public Admin guiToHost_admin(AdminUser from) {
		Admin to = new Admin();
		to.setPassword(from.getPassword());
		to.setUserName(from.getUserName());
		return to;
	}

	public AdminUser hostToGui_admin(Admin from, AdminUser to) {
		to.setRole(from.getRole());
		to.setPassword(from.getPassword());
		to.setUserName(from.getUserName());
		return to;
	}
	
	

}
