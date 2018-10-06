package gui.main.java;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gui.admin.java.AdminUser;
import gui.application.exceptions.GuiException;
import gui.application.exceptions.GuiOrderException;
import gui.main.model.java.CustomerDetails;
import gui.main.model.java.GuiProduct;
import nocturnal.host.dao.OrderDAOImpl;
import nocturnal.host.dao.ProductDAOImpl;
import nocturnal.host.dao.UserDao;
import nocturnal.host.exception.DuplicateException;
import nocturnal.host.exception.HostException;
import nocturnal.host.exception.UserLoginException;
import nocturnal.host.model.Address;
import nocturnal.host.model.Admin;
import nocturnal.host.model.Customer;
import nocturnal.host.model.Order;
import nocturnal.host.model.Product;
import nocturnal.host.model.SubOrder;

@ManagedBean
@SessionScoped
public class DbConnector {

	private static final Logger logger = LogManager.getLogger(DbConnector.class);
	private DBObjectMapper mapper = new DBObjectMapper();
	private final String searchALLquery = "from Product as prod where prod.productAvailability>0";
	private StringBuffer basicQuery = new StringBuffer("FROM Product AS prod where ");
	private ProductDAOImpl dao = new ProductDAOImpl();
	
	private Customer hostCustomer = null;
	private CustomerDetails guiCustomer;
	private Order hostOrder=null;

	public String saveNewProduct(GuiProduct prod) {

		Product hostProd = mapper.guiToHost_product(prod);

		try {
			dao.createProduct(hostProd);

			return "success";
		} catch (DuplicateException e) {
			return e.getMessage();
		} catch (HostException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}

	}

	public boolean checkIdInDB(String prodId) {

		try {
			Product prod = dao.readSingleProduct(prodId);
			if(prod!=null){
				return true;
			}
		} catch (HostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;

	}

	public List<GuiProduct> readAllFromDB() {

		return dbQueryOperation(searchALLquery);

	}

	public List<GuiProduct> dbQueryOperation(String localQuery) {
		logger.info(localQuery);
		List<GuiProduct> guiList = new LinkedList<GuiProduct>();
		// ProductDAOImpl dao = new ProductDAOImpl();

		List<Product> hostList = dao.readAll(localQuery);
		if (!(hostList == null || hostList.isEmpty())) {
			logger.info("found {} results from db, mapping begins", hostList.size());
			for (Product hostProd : hostList) {
				guiList.add(mapper.hostToGui_product(hostProd));
			}
			return guiList;
		}
		return null;

	}

	public List<GuiProduct> readKeyWord(String searchKey) {

		return dbQueryOperation(
				new StringBuffer(basicQuery).append("prod.keywords like'%" + searchKey + "%'").toString());

	}

	public String placeOrder(List<GuiProduct> selProdList, CustomerDetails cust) throws GuiOrderException {
		BigDecimal amountToPay = new BigDecimal(0);
		Order order = new Order();
		OrderDAOImpl orderDao = new OrderDAOImpl();
		ProductDAOImpl prodDao = new ProductDAOImpl();
		boolean itemsAvailable = true;
		Address deliveryAddress = null;
		try {
			for (GuiProduct prod : selProdList) {

				// 1. Map to host entity
				Product hostProd = dao.readSingleProduct(prod.getProductId());
				// 2. Create suborder
				SubOrder subOrder = new SubOrder();
				
				BigDecimal pricePerProd = new BigDecimal(prod.getProductQuantity()).multiply(prod.getProductPrice());
				amountToPay = amountToPay.add(pricePerProd);
				int availableItems = hostProd.getProductAvailability();
				int orderedItems = prod.getProductQuantity();

				if (availableItems >= orderedItems) {
					hostProd.setProductAvailability(availableItems - orderedItems);
				} else {
					itemsAvailable = false;
					throw new GuiOrderException("Available items " + availableItems + " Please order less items");
				}

				// order.getPk().setProductId(prod.getProductId());
				// order.setCustomerId(cust.getCustID());
				subOrder.setSubOrderPrice(pricePerProd);

				// Set product in subOrder with updated count of remaining
				// items.
				subOrder.setProduct(hostProd);
				subOrder.setProdQuantity(prod.getProductQuantity());
				subOrder.setSubOrderStatus("O");
				subOrder.setOfferPrice(new BigDecimal(0));
				subOrder.setSubOrderStatusReason("New Order");

				// 3.Add to main order.
				order.getSubOrderSet().add(subOrder);

			}

			// 4. Add customer to main order.
			if(guiCustomer.getIsLoggedIn()){
				
				order.setCustomer(hostCustomer);
				hostCustomer.getOrderSet().add(order);
			}
			else{
				Customer hostCust = mapper.guiToHost_customer(cust);
				Customer hostResult=null;
				
				//1.Check if customer present in host.
				try{
					hostResult = prodDao.checkIfPresentInHost(hostCust);
				}
				catch(HostException e){
					//Do nothing. Customer may not be there.
				}
				
				if(hostResult.getCustomerId()!=null){
					hostCustomer = hostResult;
					order.setCustomer(hostCustomer);
					hostCustomer.getOrderSet().add(order);
				}
				// For one time user.
				else{
					hostCustomer = hostCust;
					order.setCustomer(hostCustomer);
					hostCustomer.getOrderSet().add(order);
				}
				//2.
				//order.setCustomer();
			}
			// 5. Setting order address.
			for(Address obj : hostCustomer.getAddressSet()){
				if(obj.addressId==cust.getDeliveryAddress().addressId){
					deliveryAddress=obj;
					break;
				}
				
			}
			//order.setOrderAddress(mapper.guiToHost_address(cust.getDeliveryAddress()));
			order.setOrderAddress(deliveryAddress);
			// 6. Adding date for the order
			order.setOrderDateTime(new Date());

			order.setOrderStatus("O");
			order.setOrderStatusReason("New Order");
			order.setAmountToPay(amountToPay);
			
			
			hostOrder = orderDao.createOrder(order);
			//hostCustomer = prodDao.createCustomer(hostCustomer);
			
			//How to get order id if we save customer
			return "SUCCESS HAPPY SHOPPING";
		} catch (HostException e) {

			return e.getLocalizedMessage();
		}
	}

	public String saveNewCustomer(CustomerDetails customerDetails) {
		Customer hostCust = mapper.guiToHost_customer(customerDetails);
		ProductDAOImpl dao = new ProductDAOImpl();

		try {
			dao.createCustomer(hostCust);
			return "success";
		} catch (HostException e) {
			// TODO Auto-generated catch block
			return e.getLocalizedMessage();
		}
		

	}

	public CustomerDetails customerLogin(CustomerDetails customerDetails) throws HostException, UserLoginException{
		hostCustomer = mapper.guiToHost_customer(customerDetails);

		hostCustomer = dao.validateCustomerLogin(hostCustomer);
		
		guiCustomer = mapper.hostToGui_customer(hostCustomer, customerDetails);
			
		return guiCustomer;
		
	}

	public AdminUser adminLogin(AdminUser admin) throws GuiException, HostException, UserLoginException {
		UserDao dao = new UserDao();
		if((admin.getPassword()==null) && (admin.getUserName()==null)){
			throw new GuiException("Username or password is not provided");
		}
		
		Admin hostAdmin =  mapper.guiToHost_admin(admin);
		hostAdmin = dao.validateLogin(hostAdmin);
		
		admin = mapper.hostToGui_admin(hostAdmin, admin);
		return admin;
		
	}

}
