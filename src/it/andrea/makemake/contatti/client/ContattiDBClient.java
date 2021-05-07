package it.andrea.makemake.contatti.client;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import it.andrea.makemake.contatti.csv.CsvManager;
import it.andrea.makemake.contatti.db.hibernate.DBAndFileManager;
import it.andrea.makemake.contatti.db.hibernate.HDBManager;
import it.andrea.makemake.contatti.entity.Contatto;
import it.andrea.makemake.contatti.entity.ContattoUtils;

public class ContattiDBClient {
	private static HDBManager hdbManager = new HDBManager();
	
	public static void printMenu() {
		System.out.println("Cosa vuoi fare?");
		System.out.println("1: Visualizza rubrica");
		System.out.println("2: Aggiungi un contatto");
		System.out.println("3: Ricerca per filtri");
		System.out.println("4: Modifica un contatto");
		System.out.println("5: Rimuovi un contatto");
		System.out.println("6: Importa una rubrica da file");
		System.out.println("7: Esporta rubrica in un file");
		System.out.println("Q: Esci");
		System.out.print("La tua selezione: ");
	}

	public static Contatto createContatto(Scanner scanner) {
		System.out.println("Inserisci nome: ");
		String nome = scanner.nextLine();
		System.out.println("Inserisci cognome: ");
		String cognome = scanner.nextLine();
		System.out.println("Inserisci telefono: ");
		String telefono = scanner.nextLine();
		System.out.println("Inserisci email: ");
		String email = scanner.nextLine();
		return new Contatto(nome, cognome, telefono, email);
	}

	public static boolean addContact(Scanner scanner) {
		Contatto contatto = createContatto(scanner);
		if (!hdbManager.tableContains(contatto)) {
			hdbManager.insert(contatto);
			System.out.println("Contatto aggiunto con successo!");
			System.out.println(contatto);
			return true;
		} else {
			System.out.println("Esiste gia' un contatto con questi dati!");
			return false;
		}
	}

	public static List<Contatto> searchBy(Scanner scanner) {
		System.out.println(
				"I seguenti parametri sono opzionali, e la ricerca sarà effettuata solo in base a quelli inseriti.");
		Contatto mask = createContatto(scanner);
		List<Contatto> searchResult = hdbManager.getContattiByMask(mask);
		return searchResult;
	}

	public static void update(Scanner scanner) {
		System.out.println("Ti verranno ora richiesti alcuni dei dati del contatto che vuoi modificare.");
		List<Contatto> searchResult = searchBy(scanner);
		System.out.println("Risultati trovati: " + searchResult.size());
		ContattoUtils.printList(searchResult);
		Contatto oldContatto;
		if (searchResult.size() > 1) {
			System.out.println("Quale contatto vuoi modificare? Inserisci l'indice:");
			int index;
			do {
				index = Integer.valueOf(scanner.nextLine());
				if (index < 1 || index > searchResult.size()) {
					System.out.println("Indice non valido! Inserisci un valore tra 1 e " + searchResult.size());
				}
			} while (index < 1 || index > searchResult.size());
			oldContatto = searchResult.get(index - 1);
		} else if (searchResult.size() == 1) {
			oldContatto = searchResult.get(0);
		} else {
			System.out.println("Nessun contatto corrisponde ai filtri inseriti!");
			return;
		}
		System.out.println("Inserisci ora i dati del contatto aggiornato:");
		Contatto newContatto = createContatto(scanner);
		hdbManager.update(oldContatto, newContatto);
		System.out.println("Modifiche eseguite.");
	}

	public static void delete(Scanner scanner) {
		System.out.println("Ti verranno ora richiesti alcuni dei dati del contatto che vuoi eliminare.");
		List<Contatto> searchResult = searchBy(scanner);
		System.out.println("Risultati trovati: " + searchResult.size());
		ContattoUtils.printList(searchResult);
		Contatto greenMileContatto;
		if (searchResult.size() > 1) {
			System.out.println("Quale contatto vuoi eliminare? Inserisci l'indice:");
			int index;
			do {
				index = Integer.valueOf(scanner.nextLine());
				if (index < 1 || index > searchResult.size()) {
					System.out.println("Indice non valido! Inserisci un valore tra 1 e " + searchResult.size());
				}
			} while (index < 1 || index > searchResult.size());
			greenMileContatto = searchResult.get(index - 1);
		} else if (searchResult.size() == 1) {
			greenMileContatto = searchResult.get(0);
		} else {
			System.out.println("Nessun contatto corrisponde ai filtri inseriti!");
			return;
		}
		hdbManager.delete(greenMileContatto);
		System.out.println("Contatto eliminato.");
	}

	public static void export(Scanner scanner) {
		System.out.println("Come vuoi chiamare il nuovo file? (Senza estensioni)");
		String filename = scanner.nextLine();
		System.out.println("In che formato vuoi esportare la rubrica?");
		int format;
		do {
			System.out.println("1) .csv");
			System.out.println("2) .xml");
			format = Integer.valueOf(scanner.nextLine());
			if (format < 1 || format > 2) {
				System.out.println("Inserisci un numero valido!");
			}
		} while (format < 1 || format > 2);
		if (format == 1) {
			DBAndFileManager.exportToCsv(filename);
		} else if (format == 2) {
			// TODO
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
				ContattoUtils.printList(hdbManager.selectAll());
				break;
			case "2": // Aggiungi un contatto
				while (!addContact(scanner)) {
					// do while-condition until correct;
				}
				break;
			case "3": // Ricerca per filtri
				ContattoUtils.printList(searchBy(scanner));
				break;
			case "4": // Modifica un contatto
				update(scanner);
				break;
			case "5": // Rimuovi un contatto
				delete(scanner);
				break;
			case "6": // Importa una rubrica da file
				break;
			case "7": // Esporta rubrica in un file
				export(scanner);
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
