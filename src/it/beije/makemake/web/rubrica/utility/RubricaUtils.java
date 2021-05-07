package it.beije.makemake.web.rubrica.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.beije.makemake.web.rubrica.Contatto;

public class RubricaUtils {
	public static final File dest= new File("C:\\Users\\Padawan09\\git\\MakemakeWeb\\src\\it\\beije\\makemake\\web\\rubrica\\file\\rubrica.csv");
	public static List<Contatto> contatti=new ArrayList<>();
	
	
	public static void stampaContatti() throws IOException {
	FileReader fileReader = new FileReader(dest);
	BufferedReader bufferedReader = new BufferedReader(fileReader);
	while ( bufferedReader.ready()) {
	String line = bufferedReader.readLine();
	System.out.println(line);
	}
	fileReader.close();
}
	
	public static List<Contatto> getContactList(File orig) throws Exception {
		
		FileReader fileReader = new FileReader(orig);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while (bufferedReader.ready()) {
			String nextLine = bufferedReader.readLine();
			if (!nextLine.isEmpty() && !nextLine.contains("COGNOME")) {
				String[] contactCsv = nextLine.split(";");
				contatti.add(new Contatto(contactCsv));
			}
		}
		bufferedReader.close();
		return contatti;
	}
	public static List<Contatto> searchBy( String attribute, String value) throws Exception {
		List<Contatto> resultList = new ArrayList<Contatto>();
		switch (attribute.toLowerCase()) {
		case "nome":
			for (Contatto contatto : contatti) {
				if (contatto.getNome().equals(value)) {
					resultList.add(contatto);
				}
			}
			break;
		case "cognome":
			for (Contatto contatto : contatti) {
				if (contatto.getCognome().equals(value)) {
					resultList.add(contatto);
				}
			}
			break;
		case "telefono":
			for (Contatto contatto : contatti) {
				if (contatto.getTelefono().equals(value)) {
					resultList.add(contatto);
				}
			}
			break;
		case "email":
			for (Contatto contatto : contatti) {
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
	
	
}