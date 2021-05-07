package it.andrea.makemake.contatti.db;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import it.andrea.makemake.contatti.db.hibernate.HDBConnectionManager;
import it.andrea.makemake.contatti.entity.Contatto;

public interface DbManager {
	public List<Contatto> selectAll();

	public List<Contatto> selectBy(String filter, String value);

	/**
	 * Controlla se il contatto dato come argomento è presente nella tabella,
	 * restituendo una lista con tutte le occorrenze.
	 * 
	 * @param contatto il contatto da cercare nella tabella
	 * @return List<Contatto>
	 */
	public List<Contatto> getContattiByFullContatto(Contatto contatto);

	/**
	 * Controlla se il contatto dato come argomento è presente nella tabella,
	 * restituendo una lista con tutte le occorrenze. A differenza di
	 * getContattiByFullContatto(Contatto contatto), questo utilizza la stessa
	 * sessione del metodo chiamante.
	 * 
	 * @param contatto il contatto da cercare nella tabella
	 * @return List<Contatto>
	 */

	public List<Contatto> getContattiByMask(Contatto mask);

	public boolean tableContains(Contatto contatto);

	public void insert(Contatto contatto);

	public void insert(List<Contatto> contatti);

	public void update(Contatto oldContatto, Contatto newContatto);

	public void delete(Contatto contatto);

	public void delete(List<Contatto> contatti);
}