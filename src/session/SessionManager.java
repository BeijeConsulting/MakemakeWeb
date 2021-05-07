package session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import rubrica.Contatto;

public class SessionManager {

	private static Session config;

	private SessionManager() {}
	
	private static Session init(){
		Configuration configuration = new Configuration().configure("/rubrica/hibernate.cfg.xml")
				.addAnnotatedClass(Contatto.class);
		
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		config = session;
		return session;
	}
	
	public static Session getSession(){
		if (config == null) { 
			return init();
			}
		return config;			
	}
	public static void close(Session session) {
		config = null;
		session.close();
	}
	}

