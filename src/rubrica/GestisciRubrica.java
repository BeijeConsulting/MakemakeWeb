package rubrica;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class GestisciRubrica{

	public static void stampaMenu() {
		System.out.println("\nCosa vuoi fare? " + "\n 1) Aggiungere contatto" + "\n 2) Rimuovere contatto"
				+ "\n 3) Cercare contatto" + "\n 4) Visualizzare contatti duplicati\n" + " 5) Riordinare per nome\n" + " 6) Modifica contatto\n"+ " 7) unisci db a csv\n");

	}
	// controllo solo che non siano "" vuote e che cell sia solo numeri e mail abbia @
	public static Contatto leggiContatto(Scanner in) {
		String Nome ="", Cognome="", Telefono="", Email="";
		boolean flag = false;
		
		while (flag == false) {
			System.out.println("Inserisci nome");
			 Nome = in.nextLine();
			if (!(Nome.isEmpty())) {
				flag = true;
			}
		}
		 flag = false;
		
		while (flag == false) {
			System.out.println("Inserisci cognome");
			Cognome = in.nextLine();
			if (!(Cognome.isEmpty())) {
				flag = true;
			}
		} 
		 flag = false;
		while (flag == false) {
			System.out.println("Inserisci telefono");
			Telefono = in.nextLine();
			if (!(Telefono.isEmpty())) {
				flag = true;
			}
		}
		 flag = false;
		while (flag == false) {
			System.out.println("Inserisci email");
			Email = in.nextLine();
			if (!(Email.isEmpty())) {
				flag = true;
			}
		}
		
		Contatto contatto = new Contatto(Nome, Cognome, Telefono, Email);
		return contatto;
	}

	
	public static void duplicateContact(List<Contatto> contatti) {
		List<Contatto> duplicati = new ArrayList<Contatto>();
		boolean flag = false;
		for (int i = 0; i < contatti.size(); i++) {
			flag = false;
			for (int j = i + 1; j < contatti.size(); j++) {
				if (contatti.get(i).equals(contatti.get(j))) {
					if (duplicati.contains(contatti.get(i)))
						continue;
					flag = true;
					break;
				}
			}
			if (flag)
				duplicati.add(contatti.get(i));
		}
		if(duplicati.size()==0)
			System.out.println("Non ci sono duplicati");
		for (int i = 0; i < duplicati.size(); i++) {
			System.out.println(duplicati.get(i).toString() + " è un duplicato");
		}
	}

	public static String searchContact(List<Contatto> contatti, Contatto c) {
		for (int i = 0; i < contatti.size(); i++) {
			if (contatti.get(i).getNome().equals(c.getNome()) || contatti.get(i).getCognome().equals(c.getCognome())
					|| contatti.get(i).getTelefono().equals(c.getTelefono())
					|| contatti.get(i).getEmail().equals(c.getEmail()))
				return contatti.get(i).toString();
		}
		return "Il contatto non è presente nella lista";

	}
	public static boolean searchContact(List<Contatto> contatti, Contatto c, int bho) {
		for (int i = 0; i < contatti.size(); i++) {
			if (contatti.get(i).getNome().equals(c.getNome()) || contatti.get(i).getCognome().equals(c.getCognome())
					|| contatti.get(i).getTelefono().equals(c.getTelefono())
					|| contatti.get(i).getEmail().equals(c.getEmail()))
				return true;
		}
		return false;

	}

	public static void sortByName(List<Contatto> contatti) throws Exception  {
		Collections.sort(contatti);
	}

	public static void stampaContatti(List<Contatto> contatti) {
		for (int i = 0; i < contatti.size(); i++) {
			System.out.println(contatti.get(i).toString());
		}
	}
}
