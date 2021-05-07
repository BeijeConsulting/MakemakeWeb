package it.beije.makemake.web.Try.Rubrica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;







public class RubricaManager {
	static List<ContattoNoMap> contatti = new ArrayList<>();
	
	public static List<ContattoNoMap> readContact() throws IOException {
		contatti.clear();
		FileReader fileReader = new FileReader("C:\\Users\\Padawan02\\Desktop\\temp\\rubrica1.csv");
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while ( bufferedReader.ready()) {
			String row = bufferedReader.readLine();
			//System.out.println(row);
			String[] rowParts = row.split(";");
			ContattoNoMap contatto = new ContattoNoMap();
			contatto.setNome(rowParts[0]);
			contatto.setCognome(rowParts[1]);
			contatto.setTelefono(rowParts[2]);
			if (rowParts.length == 4)
				contatto.setEmail(rowParts[3]);
			
				contatti.add(contatto);
		}
		fileReader.close();
		return contatti;
	}
	
	public static List<ContattoNoMap> cercaContatto(String attributo,String p) {
		List<ContattoNoMap> omonimi = new ArrayList<>();
		switch(attributo) {
		case "nome" : 
			for(ContattoNoMap c : contatti) {
				if(c.getNome().equalsIgnoreCase(p)){
					omonimi.add(c);
				}
			}
			break;
		case "cognome" : 
			for(ContattoNoMap c : contatti) {
				if(c.getCognome().equalsIgnoreCase(p)) {
					 omonimi.add(c);
					 }
			}
			break;
		case "email" : 
			for(ContattoNoMap c : contatti) {
				if(c.getEmail().equalsIgnoreCase(p)) {
					omonimi.add(c);				
					}
			}
			break;

		}
		
	return omonimi;
	}
	
	public static void newContact(String nome,String cognome,String email,String telefono) {
		
		ContattoNoMap c = new ContattoNoMap();
		boolean flag = false;
		c.setNome(nome);
		c.setCognome(cognome);
		c.setTelefono(telefono);
		if (telefono!=null && !telefono.isEmpty())
			c.setEmail(email);

		
			contatti.add(c);

	}
	
	public static List<ContattoNoMap> getContatti(){
		return contatti;
	}
}
