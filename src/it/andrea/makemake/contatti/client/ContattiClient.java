package it.andrea.makemake.contatti.client;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;

import it.andrea.makemake.contatti.csv.CsvManager;
import it.andrea.makemake.contatti.entity.Contatto;

public class ContattiClient {
	public static void printMenu() {
		System.out.println("Cosa vuoi fare?");
		System.out.println("1: Visualizza rubrica");
		System.out.println("2: Aggiungi un contatto");
		System.out.println("3: Ricerca un contatto");
		System.out.println("4: Modifica un contatto");
		System.out.println("5: Rimuovi un contatto");
		System.out.println("Q: Esci");
		System.out.print("La tua selezione: ");
	}

	public static boolean addContact(Scanner scanner, File file) {
		System.out.println("Inserisci nome: ");
		String nome = scanner.nextLine();
		System.out.println("Inserisci cognome: ");
		String cognome = scanner.nextLine();
		System.out.println("Inserisci telefono: ");
		String telefono = scanner.nextLine();
		System.out.println("Inserisci email (opzionale): ");
		String email = scanner.nextLine();
		Contatto contatto = new Contatto(nome, cognome, telefono, email);
		if (addToFile(contatto, file)) {
			System.out.println("Contatto aggiunto con successo!");
			System.out.println(contatto.toString());
			return true;
		} else {
			System.out.println("Esiste gia' un contatto con questi dati!");
			return false;
		}
	}

	public static boolean addToFile(Contatto contatto, File file) {
		try {
			if (!CsvManager.containsNoOptional(contatto, CsvManager.getContactList(file))) {
				FileWriter fileWriter = new FileWriter(file, true);
				fileWriter.append(contatto.toCsv());
				fileWriter.flush();
				fileWriter.close();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static List<Contatto> searchContact(Scanner scanner, File file) throws Exception {
		List<Contatto> searchResult = CsvManager.getContactList(file);
		System.out.println("Inserisci nome: ");
		String nome = scanner.nextLine();
		searchResult = CsvManager.searchBy(searchResult, "nome", nome);
		if (searchResult.size() == 0) {
			System.out.println("Nessun contatto trovato!");
		} else {
			System.out.println("Contatti trovati: " + searchResult.size());
			CsvManager.printContactList(searchResult);
			if (searchResult.size() > 1) {
				System.out.println("Vuoi filtrare per cognome? (S/N) ");
				if (scanner.nextLine().toUpperCase().equals("S")) {
					System.out.println("Inserisci cognome: ");
					String cognome = scanner.nextLine();
					searchResult = CsvManager.searchBy(searchResult, "cognome", cognome);
					if (searchResult.size() == 0) {
						System.out.println("Nessun contatto trovato!");
					} else {
						System.out.println("Contatti trovati: " + searchResult.size());
						CsvManager.printContactList(searchResult);
					}
				}
			}
		}
		return searchResult;
	}

	public static Contatto pickContact(Scanner scanner, List<Contatto> contactList) {
		if (contactList.size() == 0) {
			System.out.println("Non ci sono contatti!");
		} else if (contactList.size() == 1) {
			return contactList.get(0);
		} else {
			System.out.println("Inserisci il numero del contatto su cui intervenire: ");
			return contactList.get(scanner.nextInt() - 1);
		}
		return null;
	}

	public static void modifyContact(Scanner scanner, File file) throws Exception {
		Contatto contatto = pickContact(scanner, searchContact(scanner, file));
		if (contatto != null) {
			Contatto modifiedContatto = new Contatto(contatto);
			System.out.println("Cosa vuoi modificare? (Nome, cognome, telefono o email. Puoi scegliere piu' di uno)");
			String selection = scanner.nextLine().toLowerCase();
			if (selection.contains("nome")) {
				System.out.print("Inserisci nuovo nome: ");
				modifiedContatto.setNome(scanner.nextLine());
			}
			if (selection.contains("cognome")) {
				System.out.print("Inserisci nuovo cognome: ");
				modifiedContatto.setCognome(scanner.nextLine());
			}
			if (selection.contains("telefono")) {
				System.out.print("Inserisci nuovo telefono: ");
				modifiedContatto.setTelefono(scanner.nextLine());
			}
			if (selection.contains("email")) {
				System.out.print("Inserisci nuova email: ");
				modifiedContatto.setEmail(scanner.nextLine());
			}
			List<Contatto> contactList = CsvManager.getContactList(file);
//			TODO
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		File file = new File(CsvManager.DEST_PATH);
		String selection;
		do {
			printMenu();
			selection = scanner.nextLine().toUpperCase();
			switch (selection) {
			case "1": // Visualizza rubrica
				CsvManager.printContactList(CsvManager.sortByName(CsvManager.getContactList(file)));
				break;
			case "2": // Aggiungi un contatto
				while (!addContact(scanner, file)) {
					// do while-condition until correct;
				}
				break;
			case "3": // Ricerca un contatto
				searchContact(scanner, file);
				break;
			case "4": // Modifica un contatto
				modifyContact(scanner, file);
				break;
			case "5": // Rimuovi un contatto
				break;
			case "Q": // Esci
				break;
			default:
				System.out.println("Comando non riconosciuto!");
				break;
			}
		} while (!selection.equals("Q"));
		scanner.close();
	}
}
