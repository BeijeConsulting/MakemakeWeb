package ecommerce;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SingletonJpa {
	private SingletonJpa() {
	}

	private static EntityManager instance;

	public static EntityManager getInstance() {
		if ( instance == null  ) {
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("MakemakeWeb");
			instance = factory.createEntityManager();
		}
		return instance;
	}
	public static void close(EntityManager entity) {
		instance = null;
		entity.close();
	}

}
