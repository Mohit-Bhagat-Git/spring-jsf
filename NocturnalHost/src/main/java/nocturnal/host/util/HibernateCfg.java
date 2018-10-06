package nocturnal.host.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import nocturnal.host.model.Product;

public class HibernateCfg {

	// private static final Logger logger =
	// LogManager.getLogger(HibernateCfg.class.getName());

	private static HibernateCfg hc = null;
	private static final Object object = new Object();
	private static SessionFactory sessionFactory;
	private static StandardServiceRegistry standardRegistry;
	private Session session;

	/*static {
		standardRegistry = new StandardServiceRegistryBuilder().configure("/hibernate.cfg.xml").build();

		Metadata metadata = new MetadataSources(standardRegistry).addAnnotatedClass(Product.class).getMetadataBuilder()
				.build();

		sessionFactory = metadata.getSessionFactoryBuilder().build();
		// return sessionFactory;
	}*/

	private HibernateCfg() {

		sessionFactory = getSessionFactory();
		// Create registry

	}

	private SessionFactory getSessionFactory() {

		try {
			standardRegistry = new StandardServiceRegistryBuilder().configure("/hibernate.cfg.xml").build();

			Metadata metadata = new MetadataSources(standardRegistry).addAnnotatedClass(Product.class)
					.getMetadataBuilder().build();
			

			sessionFactory = metadata.getSessionFactoryBuilder().build();
			return sessionFactory;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

	public static HibernateCfg getObject() {
		if (hc == null) {
			synchronized (object) {
				if (hc == null) {
					hc = new HibernateCfg();
				}
			}

		}
		return hc;
	}

	public Session getSession() {
		session = sessionFactory.openSession();
		if (!session.isConnected()) {
			System.out.println("Calling reconnect");
			// logger.warn("Open session not found");
			this.reconnect();
		}
		return session;
	}

	private void reconnect() throws HibernateException {
		this.sessionFactory = getSessionFactory();
	}
}
