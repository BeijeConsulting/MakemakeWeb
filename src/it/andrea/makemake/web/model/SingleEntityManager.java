package it.andrea.makemake.web.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SingleEntityManager {

	private static EntityManager instance;

	private SingleEntityManager() {
	}

	public static EntityManager getInstance() {
		if (instance == null || !instance.isOpen()) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("Makemake");
			instance = factory.createEntityManager();
		}
		return instance;
	}
}
