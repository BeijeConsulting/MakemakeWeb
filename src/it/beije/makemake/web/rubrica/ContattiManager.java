package it.beije.makemake.web.rubrica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContattiManager {
	public static final String rubricaDir = "C:\\Users\\Padawan09\\git\\Makemake\\src\\it\\beije\\makemake\\rubrica\\fileCsv\\rubrica2.csv";
	public static final String destDir = "C:\\Users\\Padawan09\\git\\Makemake\\src\\it\\beije\\makemake\\rubrica\\fileCsv\\rubrica_copia.csv";
	public static final File dest= new File("C:\\Users\\Padawan09\\git\\Makemake\\src\\it\\beije\\makemake\\rubrica\\fileCsv\\dest.csv");
	// metodo per caricare i contatti di una rubrica (che restituisca una lista di
	// contatti)
	public static List<ContattoRubrica> getContactList(File orig) throws Exception {
		List<ContattoRubrica> contactList = new ArrayList<ContattoRubrica>();
		FileReader fileReader = new FileReader(orig);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while (bufferedReader.ready()) {
			String nextLine = bufferedReader.readLine();
			if (!nextLine.isEmpty() && !nextLine.contains("COGNOME")) {
				String[] contactCsv = nextLine.split(";");
				contactList.add(new ContattoRubrica(contactCsv));
			}
		}
		bufferedReader.close();
		return contactList;
	}

	// metodo che scriva questa lista 
	public static void writeList(List<ContattoRubrica> contactList, File dest) throws Exception {
		FileWriter fileWriter = new FileWriter(dest, true);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		for (ContattoRubrica contatto : contactList) {
			bufferedWriter.write(contatto.toCsv() + "\n");
			
//			System.out.println("succede qualcosa");
		}bufferedWriter.flush();
		bufferedWriter.close();
	}

	// metodo che effettui la fusione di 2 file rubrica in uno solo
	public static void mergeFiles(File destFile, File mergingFile) throws Exception {
		List<ContattoRubrica> destList = getContactList(destFile);
		List<ContattoRubrica> mergingList = getContactList(mergingFile);
		FileWriter fileWriter = new FileWriter(destFile, true);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		for (ContattoRubrica contatto : mergingList) {
			if (!destList.contains(contatto)) {
				bufferedWriter.append(contatto.toCsv() + "\n");
				bufferedWriter.flush();
			}
		}
		bufferedWriter.close();
	}

	// metodo che metta in ordine alfabetico i contatti (per nome o per cognome)
	public static void sortByName(File file) throws Exception {
		List<ContattoRubrica> contactList = getContactList(file);
		Collections.sort(contactList, (o1, o2) -> o1.getNome().compareTo(o2.getNome()));
		writeList(contactList, file);
	}

	public static List<ContattoRubrica> sortByName(List<ContattoRubrica> contactList) {
		Collections.sort(contactList, (o1, o2) -> o1.getNome().compareTo(o2.getNome()));
		return contactList;
	}

	// metodo che cerchi un contatto nella rubrica (per uno qualsiasi degli
	// attributi)
	public static List<ContattoRubrica> searchBy(List<ContattoRubrica> contactList, String attribute, String value) throws Exception {
		List<ContattoRubrica> resultList = new ArrayList<ContattoRubrica>();
		switch (attribute.toLowerCase()) {
		case "nome":
			for (ContattoRubrica contatto : contactList) {
				if (contatto.getNome().equals(value)) {
					resultList.add(contatto);
				}
			}
			break;
		case "cognome":
			for (ContattoRubrica contatto : contactList) {
				if (contatto.getCognome().equals(value)) {
					resultList.add(contatto);
				}
			}
			break;
		case "telefono":
			for (ContattoRubrica contatto : contactList) {
				if (contatto.getTelefono().equals(value)) {
					resultList.add(contatto);
				}
			}
			break;
		case "email":
			for (ContattoRubrica contatto : contactList) {
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
	public static List<ContattoRubrica> findDuplicates(List<ContattoRubrica> contactList) {
		List<ContattoRubrica> duplicates = new ArrayList<ContattoRubrica>();
		for (ContattoRubrica candidate : contactList) {
			for (ContattoRubrica contatto : contactList) {
				if (candidate != contatto && candidate.equals(contatto)) {
					duplicates.add(candidate);
					break;
				}
			}
		}
		return duplicates;
	}

	public static boolean hasDuplicates(List<ContattoRubrica> contactList) {
		for (ContattoRubrica candidate : contactList) {
			for (ContattoRubrica contatto : contactList) {
				if (candidate != contatto && candidate.equals(contatto)) {
					return true;
				}
			}
		}
		return false;
}

	public static void printContactList(List<ContattoRubrica> contactList) {
		for (ContattoRubrica contatto : contactList) {
			if (!contatto.getNome().equals("NOME")) {
				System.out.println(contatto.toString());
			}
		}
	}

	public static boolean containsNoOptional(ContattoRubrica contatto, List<ContattoRubrica> contactList) {
		for (ContattoRubrica contact : contactList) {
			if (contatto.equalsNoOptional(contact)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
//		File origFile = new File(rubricaDir);
//		File copyFile = new File(dest.getPath());
//		//READ FROM FILE
//		printContactList(getContactList(origFile));
//		//COPY ON SECOND FILE:
//		writeList(getContactList(origFile), copyFile);
//		printContactList(getContactList(copyFile));
//		//MERGE FILES:
//		mergeFiles(copyFile, origFile);
//		printContactList(getContactList(copyFile));
//		//SORT FILE:
//		sortByName(copyFile);
//		printContactList(getContactList(copyFile));
//		//FIND BY VALUE:
//		printContactList(searchBy(copyFile, "nome", "mario"));
//		//FIND DUPLICATES:
//		printContactList(findDuplicates(getContactList(copyFile)));
	}
}
