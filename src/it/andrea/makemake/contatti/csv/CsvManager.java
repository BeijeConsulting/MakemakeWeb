package it.andrea.makemake.contatti.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.andrea.makemake.contatti.entity.Contatto;

public class CsvManager {
	public static final String RUBRICA_PATH = "C:\\Users\\Padawan10\\git\\Makemake\\src\\it\\andrea\\esercitazione\\contatti\\csv\\files\\rubrica1.csv";
	public static final String DEST_PATH = "C:\\Users\\Padawan10\\git\\Makemake\\src\\it\\andrea\\esercitazione\\contatti\\csv\\files\\rubrica1copy.csv";
	public static final String MAIN_DIR = "C:\\Users\\Padawan10\\git\\Makemake\\src\\it\\andrea\\esercitazione\\contatti\\csv\\files\\";

	// metodo per caricare i contatti di una rubrica (che restituisca una lista di
	// contatti)
	public static List<Contatto> getContactList(File orig) throws Exception {
		List<Contatto> contactList = new ArrayList<Contatto>();
		FileReader fileReader = new FileReader(orig);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while (bufferedReader.ready()) {
			String nextLine = bufferedReader.readLine();
			if (!nextLine.isEmpty()) {
				String[] contactCsv = nextLine.split(";");
				contactList.add(new Contatto(contactCsv));
			}
		}
		bufferedReader.close();
		return contactList;
	}

	// metodo che scriva questa lista (ve l'ho già fatto io ma vedete se potete
	// migliorarlo)
	public static void writeList(List<Contatto> contactList, File dest) throws Exception {
		FileWriter fileWriter = new FileWriter(dest);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		for (Contatto contatto : contactList) {
			bufferedWriter.write(contatto.toCsv() + "\n");
			bufferedWriter.flush();
		}
		bufferedWriter.close();
	}

	public static void writeList(List<Contatto> contactList, String filename) throws IOException {
		File file = new File(MAIN_DIR + filename + ".csv");
		FileWriter fileWriter = new FileWriter(file);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		bufferedWriter.write(new Contatto("NOME", "COGNOME", "TELEFONO", "EMAIL").toCsv() + "\n");
		bufferedWriter.flush();
		for (Contatto contatto : contactList) {
			bufferedWriter.write(contatto.toCsv() + "\n");
			bufferedWriter.flush();
		}
		bufferedWriter.close();
	}

	// metodo che effettui la fusione di 2 file rubrica in uno solo
	public static void mergeFiles(File destFile, File mergingFile) throws Exception {
		List<Contatto> destList = getContactList(destFile);
		List<Contatto> mergingList = getContactList(mergingFile);
		FileWriter fileWriter = new FileWriter(destFile, true);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		for (Contatto contatto : mergingList) {
			if (!destList.contains(contatto)) {
				bufferedWriter.append(contatto.toCsv() + "\n");
				bufferedWriter.flush();
			}
		}
		bufferedWriter.close();
	}

	// metodo che metta in ordine alfabetico i contatti (per nome o per cognome)
	public static void sortByName(File file) throws Exception {
		List<Contatto> contactList = getContactList(file);
		Collections.sort(contactList, (o1, o2) -> o1.getNome().compareTo(o2.getNome()));
		writeList(contactList, file);
	}

	public static List<Contatto> sortByName(List<Contatto> contactList) {
		Collections.sort(contactList, (o1, o2) -> o1.getNome().compareTo(o2.getNome()));
		return contactList;
	}

	// metodo che cerchi un contatto nella rubrica (per uno qualsiasi degli
	// attributi)

	public static List<Contatto> searchBy(List<Contatto> contactList, String attribute, String value) throws Exception {
		List<Contatto> resultList = new ArrayList<Contatto>();
		switch (attribute.toLowerCase()) {
		case "nome":
			for (Contatto contatto : contactList) {
				if (contatto.getNome().equals(value)) {
					resultList.add(contatto);
				}
			}
			break;
		case "cognome":
			for (Contatto contatto : contactList) {
				if (contatto.getCognome().equals(value)) {
					resultList.add(contatto);
				}
			}
			break;
		case "telefono":
			for (Contatto contatto : contactList) {
				if (contatto.getTelefono().equals(value)) {
					resultList.add(contatto);
				}
			}
			break;
		case "email":
			for (Contatto contatto : contactList) {
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

	// metodo che individui eventuali contatti duplicati
	public static List<Contatto> findDuplicates(List<Contatto> contactList) {
		List<Contatto> duplicates = new ArrayList<Contatto>();
		for (Contatto candidate : contactList) {
			for (Contatto contatto : contactList) {
				if (candidate != contatto && candidate.equals(contatto)) {
					duplicates.add(candidate);
					break;
				}
			}
		}
		return duplicates;
	}

	public static boolean hasDuplicates(List<Contatto> contactList) {
		for (Contatto candidate : contactList) {
			for (Contatto contatto : contactList) {
				if (candidate != contatto && candidate.equals(contatto)) {
					return true;
				}
			}
		}
		return false;
	}

	public static void printContactList(List<Contatto> contactList) {
		for (Contatto contatto : contactList) {
			if (!contatto.getNome().equals("NOME")) {
				System.out.println(contatto.toString());
			}
		}
	}

	public static boolean containsNoOptional(Contatto contatto, List<Contatto> contactList) {
		for (Contatto contact : contactList) {
			if (contatto.equalsNoOptional(contact)) {
				return true;
			}
		}
		return false;
	}

//	public static void main(String[] args) throws Exception {
//		File origFile = new File(RUBRICA_DIR);
//		File copyFile = new File(DEST_DIR);
////		READ FROM FILE
//		printContactList(getContactList(origFile));
////		COPY ON SECOND FILE:
//		writeList(getContactList(origFile), copyFile);
//		printContactList(getContactList(copyFile));
////		MERGE FILES:
//		mergeFiles(copyFile, origFile);
//		printContactList(getContactList(copyFile));
////		SORT FILE:
//		sortByName(copyFile);
//		printContactList(getContactList(copyFile));
////		FIND BY VALUE:
//		printContactList(searchBy(copyFile, "nome", "mario"));
////		FIND DUPLICATES:
//		printContactList(findDuplicates(getContactList(copyFile)));
//	}
}
