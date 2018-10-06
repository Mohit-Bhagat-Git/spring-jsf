package nocturnal.host.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import nocturnal.host.exception.DuplicateException;
import nocturnal.host.exception.HostException;
import nocturnal.host.exception.UserLoginException;
import nocturnal.host.model.Customer;
import nocturnal.host.model.Product;
import nocturnal.host.util.JPAUtil;

public class ProductDAOImpl implements ProductDAO {

	private static final Logger logger = LogManager.getLogger(ProductDAOImpl.class.getName());

	
	public List<Product> readAll(String query) {
		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();
			Query hql = (Query) em.createQuery(query);
			List<Product> resultList = hql.getResultList();
			if (resultList == null || resultList.isEmpty()) {
				logger.info("No product found");
				return null;

			} else {
				logger.info("{} products found", resultList.size());
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

	
	public boolean delete(String query) {
		// TODO Auto-generated method stub
		return false;
	}

	public Product readSingleProduct(String productId) throws HostException {
		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();
			Product result = em.find(Product.class, productId);
			em.flush();
			em.getTransaction().commit();
			if(result!=null){
				logger.info("Product with product id: {} found.", result.getProductId());
			}
			

			return result;
		} catch (Exception e) {
			logger.error(e);
			em.getTransaction().rollback();
			throw new HostException(e.getMessage());
		}

		finally {
			em.close();
		}

	}

	public Customer checkIfPresentInHost(Customer cust) throws HostException {
		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();
			Customer result = (Customer) em.createNamedQuery("findCustomer").setParameter("mob", cust.getMobile())
					.setParameter("fName", cust.getCustFirstName()).setParameter("lName", cust.getCustLastName())
					.getSingleResult();
			em.flush();
			em.getTransaction().commit();
			logger.info("Customer with customer id: {} found.", result.getCustomerId());

			return result;
		} catch (Exception e) {
			logger.error(e);
			
			throw new HostException(e.getMessage());
		}

		finally {
			em.close();
		}

	}

	/*
	 * (non-Javadoc) Create customer has Address object as well which will
	 * create address.
	 */
	
	public Customer createCustomer(Customer cust) throws HostException {

		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();
			//Customer result = checkIfPresentInHost(cust);
			/*if (result == null) {
				em.persist(cust);
			} else {
				throw new HostException("Customer already exist. Your Id : " + result.getCustomerId());
			}*/
			Customer result = em.merge(cust);
			em.flush();
			em.getTransaction().commit();
			logger.info("Customer with customer id: {} saved successfully and address id {}", cust.getCustomerId());

			return result;
		} catch (Exception e) {
			logger.error(e);
			
			throw new HostException(e.getMessage());
		}

		finally {
			em.close();
		}

	}

	
	public boolean createProduct(Product prod) throws DuplicateException, HostException {
		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();
			em.persist(prod);
			em.flush();
			em.getTransaction().commit();
			logger.info("Product with product id: {} saved successfully", prod.getProductId());

			return true;
		} catch (Exception e) {
			logger.error(e);
			em.getTransaction().rollback();
			throw new HostException(e.getMessage());
		}

		finally {
			em.close();
		}

	}

	public Customer validateCustomerLogin(Customer cust) throws HostException, UserLoginException {
		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();
			Customer result = (Customer) em.createNamedQuery("loginCustomer").setParameter("mob", cust.getMobile())
					.setParameter("cId", cust.getCustomerId()).setParameter("pass", cust.getPassword())
					.getSingleResult();
			em.flush();
			em.getTransaction().commit();
			logger.info("Customer with customer id: {} found!", result.getCustomerId());

			return result;
			

		} catch (NonUniqueResultException e) {
			logger.error(e);
			em.getTransaction().rollback();
			throw new HostException(e.getMessage());
		}
			
		catch(NoResultException e){
			throw new UserLoginException("Details are not correct! Contact customer care!!");
		}

		finally {
			em.close();
		}

	}

}
