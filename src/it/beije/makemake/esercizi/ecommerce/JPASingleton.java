package it.beije.makemake.esercizi.ecommerce;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPASingleton {
	private JPASingleton() {
	}
	private static EntityManager instance;
	public static EntityManager getInstance() {
		if (instance == null) {
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("MakemakeWeb");
			instance = factory.createEntityManager();
		}
		return instance;
	}
}