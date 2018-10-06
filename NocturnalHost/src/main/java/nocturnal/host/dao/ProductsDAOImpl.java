package nocturnal.host.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import nocturnal.host.configuration.HibernateCfg;
import nocturnal.host.model.Customer;
import nocturnal.host.model.Product;

public class ProductsDAOImpl implements ProductsDAO {

	private static final Logger logger = LogManager.getLogger(ProductsDAOImpl.class.getName());
	
	public List<Product> readAll(String query) {
		Session session = null;
		try{
			session= HibernateCfg.getObject().getSession();
			Query<Product> hql = session.createQuery(query);
			List<Product> resultList = hql.list();
			if(resultList==null||resultList.isEmpty()){
				logger.info("No product found");
				return null;
				
			}
			else{
				logger.info("{} products found",resultList.size());
				return resultList;
			}
		}
		catch(HibernateException e){
			logger.info(e);
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally {
			session.close();
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

	
	public boolean createCustomer(Customer cust) {
	
		Session session = null;
		try{
			session= HibernateCfg.getObject().getSession();
			session.beginTransaction();
			session.save(cust);
			Transaction tx = session.getTransaction();
			tx.commit();
			//logger.info("Product with product id: {} saved successfully",prod.getProductId());
			
			return true;
		}
		catch(Exception e){
			System.out.println(e);
			return false;
		}
		
		finally{
			session.close();
		}
		
		
	}

	
	public boolean createProduct(Product prod) {
		Session session = null;
		try{
			session= HibernateCfg.getObject().getSession();
			session.beginTransaction();
			session.save(prod);
			Transaction tx = session.getTransaction();
			tx.commit();
			//logger.info("Product with product id: {} saved successfully",prod.getProductId());
			
			return true;
		}
		catch(Exception e){
			System.out.println(e);
			return false;
		}
		
		finally{
			session.close();
		}
		
	}


}
