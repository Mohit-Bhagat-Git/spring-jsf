package nocturnal.host.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import nocturnal.host.exception.DuplicateException;
import nocturnal.host.exception.HostException;
import nocturnal.host.exception.NoResultFoundException;
import nocturnal.host.model.Customer;
import nocturnal.host.model.Order;
import nocturnal.host.model.Product;
import nocturnal.host.model.SubOrder;
import nocturnal.host.util.HibernateCfg;
import nocturnal.host.util.JPAUtil;
import nocturnal.host.util.OrderUtil;

public class OrderDAOImpl implements OrderDAO {

	private static final Logger logger = LogManager.getLogger(OrderDAOImpl.class.getName());

	
	public List<Order> readAll(String query) {
		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();
			Query hql = (Query) em.createQuery(query);
			List<Order> resultList = hql.getResultList();
			if (resultList == null || resultList.isEmpty()) {
				logger.info("No orders found");
				return null;

			} else {
				logger.info("{} orders found", resultList.size());
				return resultList;
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			em.getTransaction().commit();
			em.close();
			System.out.println("Transaction completed");
		}

		return null;
	}

	public boolean update(String query) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean bulkUpdateOrder(Set<Order> orderSet){
		EntityManager em = null;
		try {
			
			int commitInterval=10;
		
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();
			
			for(Order obj : orderSet){
				int i=0;
				em.merge(obj);
				i++;
				if(i>commitInterval){
					em.flush();
					em.clear();
					em.getTransaction().commit();
					em.getTransaction().begin();
					
				}
			}
			em.flush();
			em.getTransaction().commit();
			
			
		}
		catch(Exception e ){
			em.getTransaction().rollback();
			System.out.println(e);
		}
		finally{
			
			em.close();
		}
		return false;
	}

	public boolean updateMainOrder(Order order) throws NoResultFoundException {
		Order toBeUpdated = readSingleRecord(order.getOrderId());
		// Order updatedOrder = new OrderUtil().updateOrderStatus(order);

		if (toBeUpdated != null) {
			EntityManager em = null;
			try {
				em = JPAUtil.getEntityManagerFactory().createEntityManager();
				em.getTransaction().begin();
				toBeUpdated.getSubOrderSet().size();// Just for lazy loading
				Set<SubOrder> totalOrders = toBeUpdated.getSubOrderSet();
				for (SubOrder sub : totalOrders) {
					sub.setSubOrderStatus(order.getOrderStatus());
					sub.setSubOrderStatusReason(order.getOrderStatusReason());
					sub.setOrderCompletionTime(order.getOrderCompletionTime());

				}
				toBeUpdated.setOrderStatus(order.getOrderStatus());
				toBeUpdated.setOrderStatusReason(order.getOrderStatusReason());
				toBeUpdated.setOrderCompletionTime(order.getOrderCompletionTime());
				em.merge(toBeUpdated);
				em.getTransaction().commit();
			} catch (Exception e) {
				em.getTransaction().rollback();
				logger.error("Some exception occured {}", e);
				return false;
			} finally {
				em.close();
			}
			return true;
		} else {
			throw new NoResultFoundException("Strange!! No such order available.");
		}

	}

	
	public boolean delete(String query) {
		// TODO Auto-generated method stub
		return false;
	}


	public Order readSingleRecord(Long orderId) {

		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Order result = em.find(Order.class, orderId);
			em.flush();
			em.getTransaction().commit();
			logger.info("Result found successfully");
			return result;

		} catch (Exception e) {
			logger.error(e);
			em.getTransaction().rollback();
			return null;
		}

		finally {
			em.close();
		}
	}

	public Order createOrder(Order order) throws HostException {
		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Order result = em.merge(order);

			em.flush();
			em.getTransaction().commit();
			logger.info("order with order id: {} saved successfully", result.getOrderId());

			return result;
		} catch (Exception e) {
			logger.error(e);
			em.getTransaction().rollback();
			throw new HostException("Problem occured while saving order. Try again!" + e.getMessage());
		}

		finally {
			em.close();
		}

	}

}
