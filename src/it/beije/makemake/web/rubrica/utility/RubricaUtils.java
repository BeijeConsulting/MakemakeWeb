package it.beije.makemake.web.rubrica.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import it.beije.makemake.web.esercizi.SingletonJpa;
import it.beije.makemake.web.rubrica.ContattoRubrica;

public class RubricaUtils {
	public static final File dest = new File(
			"C:\\Users\\Padawan09\\git\\MakemakeWeb\\src\\it\\beije\\makemake\\web\\rubrica.csv");
	public static List<ContattoRubrica> contatti = new ArrayList<>();

	public static void stampaContatti() throws IOException {
		FileReader fileReader = new FileReader(dest);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while (bufferedReader.ready()) {
			String line = bufferedReader.readLine();
			System.out.println(line);
		}
		fileReader.close();
	}

	public static List<ContattoRubrica> getContactList(File orig) throws Exception {

		FileReader fileReader = new FileReader(orig);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while (bufferedReader.ready()) {
			String nextLine = bufferedReader.readLine();
			if (!nextLine.isEmpty() && !nextLine.contains("COGNOME")) {
				String[] contactCsv = nextLine.split(";");
				contatti.add(new ContattoRubrica(contactCsv));
			}
		}
		bufferedReader.close();
		return contatti;
	}

	public static List<ContattoRubrica> searchBy(String attribute, String value) throws Exception {
		List<ContattoRubrica> resultList = new ArrayList<ContattoRubrica>();
		switch (attribute.toLowerCase()) {
		case "nome":
			for (ContattoRubrica contatto : contatti) {
				if (contatto.getNome().equals(value)) {
					resultList.add(contatto);
				}
			}
			break;
		case "cognome":
			for (ContattoRubrica contatto : contatti) {
				if (contatto.getCognome().equals(value)) {
					resultList.add(contatto);
				}
			}
			break;
		case "telefono":
			for (ContattoRubrica contatto : contatti) {
				if (contatto.getTelefono().equals(value)) {
					resultList.add(contatto);
				}
			}
			break;
		case "email":
			for (ContattoRubrica contatto : contatti) {
				if (contatto.getEmail().equals(value)) {
					resultList.add(contatto);
				}
			}
			break;
		default:
			System.out.println("Proprieta' errata o non esistente!");
			break;
		}
		return resultList;
	}

	// MOSTRA RUBRICA
	public static String mostraRubrica() {
		EntityManager entityManager = SingletonJpa.getInstance();
		String jpqlSelect = "SELECT c FROM ContattoRubrica as c";
		Query query = entityManager.createQuery(jpqlSelect);
		List<ContattoRubrica> contatti = query.getResultList();

		StringBuilder output = new StringBuilder();
		for (ContattoRubrica contatto : contatti) {
			output.append("<br>CONTATTO=[" + contatto + "]");
			// output.append("\nCONTATTO=[" + contatto +"]\n");

		}
		String respcontact = output.toString();
		return respcontact;
	}

	// MODIFICA CONTATTO NON VA
	public static void modificaContatto() {
		EntityManager entityManager = SingletonJpa.getInstance();

		ContattoRubrica c = entityManager.find(ContattoRubrica.class, 20);
		System.out.println(c);
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		try {
			c.setNome("Gianluca");
			c.setCognome("Bertolasi");
			c.setTelefono("55673495");
			c.setEmail("g.bertolasi@gmail.com");
			entityManager.persist(c);
			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
		} finally {
			entityManager.close();
		}

	}

	// INSERIMENTO CONTATTO
	public static void inserisciContatto(String nome, String cognome, String email, String telefono) {
		EntityManager entityManager = SingletonJpa.getInstance();

		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		
			ContattoRubrica newContatto = new ContattoRubrica();
			newContatto.setCognome(cognome);
			newContatto.setNome(nome);
			newContatto.setEmail(email);
			newContatto.setTelefono(telefono);
			System.out.println("contatto PRE : " + newContatto);
			entityManager.persist(newContatto);
			System.out.println("contatto POST : " + newContatto);
			entityTransaction.commit();
		

	}

	// CANCELLA DA RUBRICA NON VA
	public static void cancellaContatto() {
		EntityManager entityManager = SingletonJpa.getInstance();
		ContattoRubrica c = entityManager.find(ContattoRubrica.class, 26);
		System.out.println(c);
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		try {
			entityManager.remove(c);
			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
		} finally {
			entityManager.close();
		}
	}

}