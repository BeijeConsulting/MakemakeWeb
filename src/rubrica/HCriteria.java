package rubrica;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;


public class HCriteria {
	//public static Session session = SessionManager.getSession();

	public static String modifyContact(Session session,Contatto c,String operazione ,String valore) throws Exception {
		// 1nome 2 cognome 3 email 4 tel
		System.out.println(c);
		if(selectContact(c, session) == null)
			return"IL CONTATTO INSERITO NON E' IN RUBRICA";
		else {
			List<Contatto> contatti = selectAll(session);
			for (Contatto c1 : contatti) {
				if(c1.equals(c)) {
					Transaction transaction = session.beginTransaction();
					switch (operazione) {
					case "1":
						c1.setNome(valore);
						break;
					case "2":
						c1.setCognome(valore);
						break;
					case "3":
						c1.setEmail(valore);
						break;
					case "4":
						c1.setTelefono(valore);
						break;
					}
					session.save(c1);
					transaction.commit();
					System.out.println("CONTATTO AGGIORNATO");
				}
				
			}
			
			return null;
		}
		
			
	}

	public static List<Contatto> selectAll(Session session) throws Exception {

		Criteria criteria = session.createCriteria(Contatto.class);

		List<Contatto> contatti = criteria.list();
//		
//		for(Contatto c: contatti) {
//			System.out.println(c);
//		}
		return contatti;

	}
	
	public static List<Contatto> selectContact(Contatto c, Session session) throws Exception {
		
		Criteria criteria = session.createCriteria(Contatto.class);
		criteria.add(Restrictions.eq("nome", c.getNome())).add(Restrictions.eq("cognome", c.getCognome()))
				.add(Restrictions.eq("email", c.getEmail())).add(Restrictions.eq("telefono", c.getTelefono()));
		List<Contatto> contatti = criteria.list();

		if(contatti.size()==0)
			return null;
		
		return contatti;

	}
	
	public static void deleteContact(Contatto c, Session session) throws Exception {
		
		List<Contatto> contatti = selectAll(session);
		for (Contatto c1 : contatti) {
			if(c1.equals(c)) {
				Transaction transaction = session.beginTransaction();
				session.delete(c1);
				transaction.commit();
				System.out.println("COntatto eliminato");
			}
			
		}
}
	
	public static void addContact(Contatto c, Session session) throws Exception {
		Transaction transaction = session.beginTransaction();
		session.save(c);
		transaction.commit();
		System.out.println("Contatto inserito");

	}
}
