package it.beije.makemake.database;

import it.beije.makemake.addressbook.Contact;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SessionManager {

    private SessionManager() {
        Configuration configuration = new Configuration().configure()
                .addAnnotatedClass(Contact.class);
        this.sessionFactory = configuration.buildSessionFactory();
    }

    private static SessionManager instance;
    private SessionFactory sessionFactory;
    private List<Session> sessionList = new ArrayList<>();

    public static SessionManager getSessionManager() {
        if (instance == null) {
            instance = new SessionManager();
        }

        return instance;
    }

    public Session getSession() {
        Session session;
        for (Session s :
                sessionList) {
            if (!s.isOpen()) {
                s.close();
                sessionList.remove(s);
            }
        }
        if (sessionList.size() == 150) {
            throw new SessionException("Max number of concurrent sessions reached");
        } else {
            session = sessionFactory.openSession();
            sessionList.add(session);
        }
        return session;
    }

    public void closeSession(Session session) {
        session.close();
        sessionList.remove(session);
    }

}