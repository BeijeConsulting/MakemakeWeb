package rubrica;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SingletonJpa {
	private SingletonJpa() {
	}

	private static EntityManager instance;

	public static EntityManager getInstance() {
		if (instance == null) {
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("Makemake");
			instance = factory.createEntityManager();
		}
		return instance;
	}

}
