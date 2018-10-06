package gui.main.java;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import gui.configuration.java.Configurator;
import gui.main.model.java.GuiProduct;
import nocturnal.host.model.Product;

@ManagedBean(name="prodConf")
@SessionScoped
public class ProductConfigured {
	
	
	private List<GuiProduct> allProducts;
	
	
	private GuiProduct product;

	public List<GuiProduct> readAllFromConfiguration() {
		List<String> prodDetails;
		prodDetails = Configurator.getObject().readAllValues();
		return readProductDetails(prodDetails);
	}

	private List<GuiProduct> readProductDetails(List<String> prodDetails) {
		
		List<GuiProduct> productList = new LinkedList<GuiProduct>();
		for(String s : prodDetails){
			product = new GuiProduct();
			int i=0;
			
			StringTokenizer token = new StringTokenizer(s, "|");
			String[] array = new String[token.countTokens()];
			while(token.hasMoreTokens()){
				array[i] = token.nextToken() ;
				i++;
			}
			//Adding into list
			//Chips|Lays|25|10|LaysIndiaMM_25_10.img|IndiaMM
			product.setProductCategory(array[0]);
			product.setProductBrand(array[1]);
			//product.setProductSize(array[2]);
			//product.setProductPrice(array[3]);
			product.setImageName(array[4]);
			product.setProductId(array[4]);
			product.setProductName(array[5]);
			productList.add(product);
			
		}
		
		
		
		
		return productList;
	}
	
	

}
