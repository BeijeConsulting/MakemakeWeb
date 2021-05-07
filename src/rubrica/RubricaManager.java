package rubrica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RubricaManager {
	
	public static List<Contatto> contatto = new ArrayList<>();
	
	
	
	
	
	public static void printRubrica() throws IOException {
	
	FileReader fileReader = new FileReader("C:\\Users\\Padawan04\\Desktop\\rubrica.txt");
	BufferedReader bufferedReader = new BufferedReader(fileReader);
	while ( bufferedReader.ready()) {
	String line = bufferedReader.readLine();
	System.out.println(line);
	}
	fileReader.close();
}
	
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
	
}
