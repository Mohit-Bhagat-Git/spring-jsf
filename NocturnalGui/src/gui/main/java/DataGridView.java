package gui.main.java;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gui.main.model.java.GuiProduct;


@ManagedBean
@SessionScoped
public class DataGridView {
	
	private static final Logger logger = LogManager.getLogger(DataGridView.class);
	private List<GuiProduct> allProducts;
	private GuiProduct selectedProduct;
	private Set<GuiProduct>  setProducts;
	
	public Set<GuiProduct> getSetProducts() {
		return setProducts;
	}

	public void setSetProducts(Set<GuiProduct> setProducts) {
		this.setProducts = setProducts;
	}

	@ManagedProperty(value = "#{prodConf}")
	private ProductConfigured prodConf;
	
	@ManagedProperty(value ="#{dbConnector}")
	private DbConnector dbConn;
	
	/*private List<Smoke> allSmokes;
	private List<Chips> allChips;*/

	@PostConstruct
	public void readAllProducts(){
		
		logger.trace("Reding all products");
		
		allProducts=dbConn.readAllFromDB();
		
		setProducts = new HashSet(allProducts);
		
		logger.info("search complete");
		
	}

	public List<GuiProduct> getAllProducts() {
		return allProducts;
	}

	public void setAllProducts(List<GuiProduct> allProducts) {
		this.allProducts = allProducts;
	}

	public GuiProduct getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(GuiProduct selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public ProductConfigured getProdConf() {
		return prodConf;
	}

	public void setProdConf(ProductConfigured prodConf) {
		this.prodConf = prodConf;
	}

	public DbConnector getDbConn() {
		return dbConn;
	}

	public void setDbConn(DbConnector dbConn) {
		this.dbConn = dbConn;
	}

}
