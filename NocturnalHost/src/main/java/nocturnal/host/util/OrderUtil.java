package nocturnal.host.util;

import java.util.Set;

import javax.persistence.NoResultException;

import com.sun.media.jfxmedia.logging.Logger;

import nocturnal.host.dao.OrderDAOImpl;
import nocturnal.host.exception.NoResultFoundException;
import nocturnal.host.model.Order;
import nocturnal.host.model.SubOrder;

public class OrderUtil {

	/*public Order updateOrderStatus(Order order) throws NoResultFoundException {

		// C stands for cancel
		// O stands for Open
		// R stands for Return
		
		OrderDAOImpl dao = new OrderDAOImpl();
		Order result  = dao.readSingle(order);
		if(result!=null){
			Set<SubOrder> totalOrders = order.getSubOrderSet();
			for (SubOrder sub : totalOrders) {
				sub.setSubOrderStatus(order.getOrderStatus());
				sub.setSubOrderStatusReason(order.getOrderStatusReason());
			}
			return order;
		}
		else{
			throw new NoResultFoundException("Strange!! No such order available.");
		}*/
		

}
