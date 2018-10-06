package nocturnal.host.dao;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import nocturnal.host.exception.HostException;
import nocturnal.host.exception.UserLoginException;
import nocturnal.host.model.Admin;
import nocturnal.host.model.Customer;
import nocturnal.host.util.JPAUtil;

public class UserDao {
	
	private static final Logger logger = LogManager.getLogger(UserDao.class.getName());

	public Admin validateLogin(Admin admin) throws HostException, UserLoginException {
		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();
			Admin result = (Admin) em.createNamedQuery("loginAdmin").setParameter("pass", admin.getPassword())
					.setParameter("user", admin.getUserName())
					.getSingleResult();
			em.flush();
			em.getTransaction().commit();
			logger.info("Admin found!");

			return result;
			

		} catch (NonUniqueResultException e) {
			logger.error(e);
			em.getTransaction().rollback();
			throw new HostException(e.getMessage());
		}
			
		catch(NoResultException e){
			throw new UserLoginException(e.getLocalizedMessage());
		}

		finally {
			em.close();
		}

	}

	
	
}
