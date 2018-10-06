package gui.test.main;

import org.junit.Test;

import gui.admin.java.OrderService;
import gui.main.java.Actions;
import gui.main.java.DbConnector;
import gui.main.model.java.GuiOrder;
import gui.main.model.java.GuiProduct;

public class GuiTest {

	
	@Test
	public void searchOrderAdmin(){
		
		GuiOrder mainOrder =  new GuiOrder();
		mainOrder.setOrderId(1000000000l);
		
		OrderService service = new OrderService();
		service.readAllOrders(mainOrder);
		
	}
	//@Test
	public void TestCreateProduct(){
		
		GuiProduct prod = new GuiProduct();
		//prod.setProductId("CHIPLAYS20xx40xxAMCRxx");
		prod.setProductCategory("Chips");
		prod.setProductBrand("Lays");
		prod.setProductSize("40");
		prod.setProductPrice("40");
		prod.setProductName("India's Magic Masala");
		//prod.setImageName("CHIPLAYS20xx40xxINMAMA");
		prod.setKeywords("Lays MagicMasala LaysBlue");
		prod.setProductQuantity(100);
		
		Actions action = new Actions();
	
		
		
		DbConnector dbConn = new DbConnector();
		dbConn.saveNewProduct(prod);
		
	}
	
	//@Test
	public void TestProdId(){
		
		GuiProduct prod = new GuiProduct();
		//prod.setProductId("CHIPLAYS20xx40xxAMCR");
		prod.setProductCategory("Chips");
		prod.setProductBrand("Lays");
		prod.setProductSize("20");
		prod.setProductPrice("40");
		prod.setProductName("American Cream");
		//prod.setImageName("CHIPLAYS20xx40xxINMAMA");
		prod.setKeywords("Lays American LaysYellow");
		prod.setProductQuantity(100);
		
		Actions action = new Actions();
		action.genetrateProductId(prod);
	}
	
	//@Test
	public void testSearchProduct(){
		
		String query = "FROM Product AS prod where prod.productAvailability>0";
		DbConnector dbConn = new DbConnector();
		dbConn.dbQueryOperation(query);
		
		
		
	}
	
	public void createOrder(){
		
		
	}
}
