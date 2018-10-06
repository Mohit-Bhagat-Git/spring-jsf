package nocturnal.host.dao;

import java.util.List;

import nocturnal.host.model.Customer;
import nocturnal.host.model.Product;

public interface ProductsDAO {
	
	public List<Product> readAll(String query);
	public boolean update(String query);
	public boolean delete(String query);
	public boolean createProduct(Product prod);
	public boolean createCustomer(Customer cust);

}
