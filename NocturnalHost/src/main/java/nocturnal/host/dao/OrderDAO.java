package nocturnal.host.dao;

import java.util.List;

import nocturnal.host.exception.DuplicateException;
import nocturnal.host.exception.HostException;
import nocturnal.host.model.Customer;
import nocturnal.host.model.Order;
import nocturnal.host.model.Product;

public interface OrderDAO {
	
	public List<Order> readAll(String query);
	public boolean update(String query);
	public boolean delete(String query);
	public Order readSingleRecord(Long orderId);
	Order createOrder(Order prod) throws HostException;
	

}
