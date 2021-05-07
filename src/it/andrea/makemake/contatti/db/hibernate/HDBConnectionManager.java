package it.andrea.makemake.contatti.db.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import it.andrea.makemake.contatti.db.TooManySessionsException;
import it.andrea.makemake.contatti.entity.Contatto;

public class HDBConnectionManager {
	private static final int MAX_SESSIONS = 5;

	private static List<Session> sessions = new ArrayList<Session>();

	private HDBConnectionManager() {
	}

	public static Session getSession() throws TooManySessionsException {
		if (sessions.size() < MAX_SESSIONS) {
			Configuration configuration = new Configuration().configure().addAnnotatedClass(Contatto.class);
			SessionFactory sessionFactory = configuration.buildSessionFactory();
			Session session = sessionFactory.openSession();
			sessions.add(session);
			return session;
		}
		throw new TooManySessionsException();
	}

	public static void closeSession(Session session) {
		session.close();
		sessions.remove(session);
	}
}
