package gui.main.java;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gui.application.exceptions.GuiOrderException;
import gui.configuration.java.Configurator;
import gui.main.model.java.CustomerAddress;
import gui.main.model.java.CustomerDetails;
import gui.main.model.java.GuiProduct;
import gui.main.pojo.java.Cart;
import gui.session.java.UserSession;
import nocturnal.host.exception.HostException;
import nocturnal.host.exception.UserLoginException;

@ManagedBean(name = "actions")
@SessionScoped
public class Actions {

	private static final Logger logger = LogManager.getLogger(Actions.class.getName());

	@ManagedProperty(value = "#{guiProduct}")
	private GuiProduct prod;

	@ManagedProperty(value = "#{dbConnector}")
	private DbConnector dbConn;

	@ManagedProperty(value = "#{dataGridView}")
	private DataGridView dataGridView;

	/*@ManagedProperty(value = "#{cart}")
	private Cart cart;*/

	@ManagedProperty(value = "#{customerDetails}")
	private CustomerDetails customerDetails;

	@ManagedProperty(value = "#{customerAddress}")
	private CustomerAddress customerAddress;

	private String searchKey;

	@PostConstruct
	public void registered() {
		logger.info("actions registered");
	}

	
	public String customerProfile() {

		if (!customerDetails.getIsLoggedIn()) {
			return "customer.xhtml?faces-redirect=true";
		}
		return "customerProfile.xhtml?faces-redirect=true";
	}

	public void login() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			//Null check the credentials
			if(customerDetails.getPassword()==null || (customerDetails.getMobileNumber()==null && customerDetails.getCustID()==null)){
				context.addMessage(null, new FacesMessage("Please check your credentials."));
				ExternalContext ec = context.getExternalContext();
				String rootPath = ec.getApplicationContextPath();
				ec.redirect(rootPath+"/pages/public/customer.xhtml");
			}
			
			//Make a db call
			customerDetails = dbConn.customerLogin(customerDetails);
			customerDetails.setIsLoggedIn(true);
			HttpSession session  = (HttpSession) context.getExternalContext().getSession(false);
			UserSession userSession = (UserSession) session.getAttribute("userSession");
			
			//We must get userSession to ensure session activity.
			if(userSession==null){
				context.addMessage(null, new FacesMessage("Could not log you in. Please try again!"));
				ExternalContext ec = context.getExternalContext();
				String rootPath = ec.getApplicationContextPath();
				ec.redirect(rootPath+"/pages/public/customer.xhtml");
				return;
			}
			userSession.setCustomer(customerDetails);
			
			
			context.addMessage(null, new FacesMessage("Login Success"));
		} catch (HostException e) {
			context.addMessage(null, new FacesMessage(e.getLocalizedMessage()));
		} catch (UserLoginException e) {
			context.addMessage(null, new FacesMessage(e.getLocalizedMessage()));
		}
		catch(Exception e){
			logger.error("Some unknown exception occured {}",e);
		}
	}

	public void logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		// customerDetails.setIsLoggedIn(false);
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		context.addMessage(null, new FacesMessage("Logout Success"));
	}

	public void addToCart(GuiProduct selectedProd) {

		/*
		 * Map<String, GuiProduct> tempMap = cart.getTotalItems(); final int
		 * initialSize = tempMap.size();
		 * tempMap.put(selectedProd.getProductId(), selectedProd);
		 */
		
		if (customerDetails.getCart().getCartProdList().contains(selectedProd)) {
			customerDetails.getCart().getCartProdList().remove(selectedProd);
		}
		customerDetails.getCart().getCartProdList().add(selectedProd);
		logger.info("Selected product items list {}", customerDetails.getCart().getCartProdList().size());
		// cart.setTotalItems(tempMap);
		// cart.setTotalItems(totalItems);

	}

	public void removeFromCart(GuiProduct selectedProd) {
		logger.trace("Removing prod with id {}", selectedProd.getProductId());
		// cart.getTotalItems().remove(selectedProd.getProductId());
		customerDetails.getCart().getCartProdList().remove(selectedProd);
		logger.trace("Product removed success fully");
	}

	public String orderProceed() {
		// Step1: address validity
		if (!isCustomerPresent()) {
			return "customer.xhtml?faces-redirect=true";
		} else {
			return "orderAddress.xhtml?faces-redirect=true";
		}

	}

	public String orderConfirm() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			String message  = dbConn.placeOrder(customerDetails.getCart().getCartProdList(), customerDetails);
			context.addMessage(null, new FacesMessage(message));
			return "orderReview.xhtml?faces-redirect=true";
		} catch (GuiOrderException e) {
			e.printStackTrace();
			context.addMessage(null, new FacesMessage("FAILED  :( TRY AGAIN!!"));
			return "orderReview.xhtml?faces-redirect=true";
		}
		// if everything ok redirect to confirmation page make cart bean null
		
	}

	private boolean isCustomerPresent() {
		return customerDetails.getIsLoggedIn();
	}

	public void saveCustomerDetails() {
		if (!isCustomerPresent())// here make a call to db to check whether
									// customer already exists and return cust
									// id.
		{
			customerDetails.getAddressSet().add(customerAddress);
			String message = null;

			message = dbConn.saveNewCustomer(customerDetails);

			logger.info(message);
			FacesContext context = FacesContext.getCurrentInstance();
			/*
			 * String value =
			 * context.getApplication().evaluateExpressionGet(context,
			 * "#{lbl['customerFirstName']}", String.class);
			 * 
			 * logger.info("value from expression : customerFirstName {}",value)
			 * ;
			 */

			String confValue = Configurator.getObject().getPropValues("customerAlreadyExist");

			context.addMessage(null, new FacesMessage(message));
		}

	}
	/*
	 * public void increaseProdQuantity() {
	 * 
	 * int number = prod.getProductQuantity(); number++;
	 * logger.info("Product quantity incremented {}", number);
	 * prod.setProductQuantity(number);
	 * 
	 * FacesContext context = FacesContext.getCurrentInstance(); UIViewRoot root
	 * = context.getViewRoot();
	 * 
	 * final String componentId = "output2"; UIComponent c = findComponent(root,
	 * componentId); System.out.println(c.getClientId(context)); }
	 * 
	 * private UIComponent findComponent(UIComponent c, String id) { if
	 * (id.equals(c.getId())) { return c; } Iterator<UIComponent> kids =
	 * c.getFacetsAndChildren(); while (kids.hasNext()) { UIComponent found =
	 * findComponent(kids.next(), id); if (found != null) { return found; } }
	 * return null; }
	 */

	public void searchKeyword() {

		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("search clicked"));

		dataGridView.setAllProducts(dbConn.readKeyWord(searchKey));
	}

	public void saveProduct() {
		FacesContext context = FacesContext.getCurrentInstance();
		logger.info("Product details are ");
		String prodId = genetrateProductId(prod);
		// Check for the prod id in database
		if (!dbConn.checkIdInDB(prodId)) {
			// if not found then assign same productid as image name
			prod.setImageName(prodId);
			prod.setProductId(prodId);
			// Save in database
			String message = dbConn.saveNewProduct(prod);
			context.addMessage(null, new FacesMessage(message));
		}

		else {

			// Faces.getexternam blahh blahh log error message
		}

		logger.info(prodId);
	}

	public String genetrateProductId(GuiProduct prod) {
		StringBuffer sb = new StringBuffer();
		if (prod.getProductCategory() != null) {
			getFirstBytes(prod.getProductCategory().toUpperCase(), sb);

		}

		if (prod.getProductBrand() != null) {
			getFirstBytes(prod.getProductBrand().toUpperCase(), sb);

		}

		if (prod.getProductSize() != 0) {
			getFirstBytes(String.valueOf(prod.getProductSize()), sb);

		}

		if (prod.getProductPrice() != null || prod.getProductPrice() != new BigDecimal(0)) {
			getFirstBytes(String.valueOf(prod.getProductPrice()), sb);

		}

		if (prod.getProductName() != null) {
			handleProductName(prod.getProductName().toUpperCase(), sb);

		}
		logger.info("generated product id : " + sb.toString());
		return sb.toString();

	}

	private StringBuffer handleProductName(String upperCase, StringBuffer sb) {
		String string = upperCase.trim();
		// Check for spaces
		String[] splitted = string.split(" ");
		for (String s : splitted) {
			sb.append(s.subSequence(0, 2));
		}
		while (sb.toString().length() < 22) {
			sb.append("x");
		}
		return sb;

	}

	private StringBuffer getFirstBytes(String upperCase, StringBuffer sb) {
		String idVariable = null;
		String string = upperCase.trim();
		if (string.length() >= 4) {
			idVariable = (String) string.subSequence(0, 4);
			return sb.append(idVariable);
		} else {
			int length = string.length();
			sb.append(string);
			/*
			 * for(int i = length; i<4;i++ ){ sb.append("x"); }
			 */
			while (length < 4) {
				sb.append("x");
				length++;
			}
			return sb;
		}

	}

	public GuiProduct getProd() {
		return prod;
	}

	public void setProd(GuiProduct prod) {
		this.prod = prod;
	}

	public DbConnector getDbConn() {
		return dbConn;
	}

	public void setDbConn(DbConnector dbConn) {
		this.dbConn = dbConn;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public DataGridView getDataGridView() {
		return dataGridView;
	}

	public void setDataGridView(DataGridView dataGridView) {
		this.dataGridView = dataGridView;
	}

	/*public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}*/

	public CustomerDetails getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(CustomerDetails customerDetails) {
		this.customerDetails = customerDetails;
	}

	public CustomerAddress getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(CustomerAddress customerAddress) {
		this.customerAddress = customerAddress;
	}

}
