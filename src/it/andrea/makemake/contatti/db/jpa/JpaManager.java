package it.andrea.makemake.contatti.db.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import it.andrea.makemake.SingleEntityManager;
import it.andrea.makemake.contatti.db.DbManager;
import it.andrea.makemake.contatti.entity.Contatto;

public class JpaManager implements DbManager {
	@Override
	public List<Contatto> selectAll() {
		EntityManager em = SingleEntityManager.getInstance();
		TypedQuery<Contatto> query = em.createQuery("SELECT c FROM Contatto c", Contatto.class);
		List<Contatto> result = query.getResultList();
		em.close();
		return result;
	}

	@Override
	public List<Contatto> selectBy(String filter, String value) {
		return null;
	}

	@Override
	public List<Contatto> getContattiByFullContatto(Contatto contatto) {
		return null;
	}

	@Override
	public List<Contatto> getContattiByMask(Contatto mask) {
		return null;
	}

	@Override
	public boolean tableContains(Contatto contatto) {
		return false;
	}

	@Override
	public void insert(Contatto contatto) {
		EntityManager em = SingleEntityManager.getInstance();
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();
		em.persist(contatto);
		entityTransaction.commit();
		em.close();
	}

	@Override
	public void insert(List<Contatto> contatti) {
	}

	@Override
	public void update(Contatto oldContatto, Contatto newContatto) {
	}

	@Override
	public void delete(Contatto contatto) {
	}

	@Override
	public void delete(List<Contatto> contatti) {
	}
}
