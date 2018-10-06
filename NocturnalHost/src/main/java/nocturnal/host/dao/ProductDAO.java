package nocturnal.host.dao;

import java.util.List;

import nocturnal.host.exception.DuplicateException;
import nocturnal.host.exception.HostCustomerException;
import nocturnal.host.exception.HostException;
import nocturnal.host.model.Customer;
import nocturnal.host.model.Product;

public interface ProductDAO {
	
	public List<Product> readAll(String query);
	public boolean update(String query);
	public boolean delete(String query);
	public boolean createProduct(Product prod) throws DuplicateException, HostException;
	public Customer createCustomer(Customer cust) throws HostException;

}
