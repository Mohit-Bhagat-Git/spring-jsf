package spring.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.model.Product;
import spring.service.ProductService;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService service;
	
	
	@RequestMapping(value = "/readAll", method=RequestMethod.GET)
	public Collection<Product> readAll(){
		List<Product> listProduct = service.getAllProduct();
		System.out.println(listProduct.size() +" Entries found");
		Collection<Product> collection = listProduct;
		return collection;
	}
	
	@RequestMapping(value = "/emReadByKeyword/{keyword}", method=RequestMethod.GET)
	public Collection<Product> readProductByCriteria(@PathVariable String keyword){
		List<Product> listProduct = service.emFindByKeyword(keyword);
		System.out.println(listProduct.size() +" Entries found");
		Collection<Product> collection = listProduct;
		return collection;
	}
	
	
	@RequestMapping(value="/getById/{id}", method=RequestMethod.GET)
	public Product getById(@PathVariable String id){
		Product product =  service.getById(id);
		return product;
	}
	
	@RequestMapping(value = "/saveOrUpdateProduct", method=RequestMethod.POST)
	public Product saveOrUpdate(@RequestBody Product Product){
		Product resultProduct =  service.saveOrUpdateProd(Product);
		return resultProduct;
	}
	
	/**
	 * @param Product
	 * @return This method always return true even if we pass a record which is not present in the database.
	 * 
	 * As of now solution could be to use entity manager directly inside Service class.
	 */
	@RequestMapping(value = "/deleteProduct", method=RequestMethod.DELETE)
	public boolean delete(@RequestBody Product Product){
		boolean deleted =  service.deleteProd(Product);
		return deleted;
	}
	
	
	@RequestMapping(value = "/emDelete", method=RequestMethod.DELETE)
	public boolean deleteEm(@RequestBody Product Product){
		boolean deleted =  service.emDelete(Product);
		return deleted;
	}
	
	@RequestMapping(value = "/deleteProductById", method=RequestMethod.DELETE)
	public boolean deleteProductbyId(@RequestBody Product Product){
		boolean deleted =  service.deleteProductbyId(Product);
		return deleted;
	}
	
	
	@RequestMapping(value = "/alive", method=RequestMethod.GET)
	public String aliveCheck(){
		String message = "Product Controller is alive!!";
		System.out.println(message);
		return message;
	}
}
