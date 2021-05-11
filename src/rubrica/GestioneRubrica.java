package rubrica;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class GestioneRubrica {
	
	public static void main(String[] args) {
		String riga;
		
		String percorsoFile="C:\\Users\\Padawan05\\Desktop\\FileJava\\";
		String fileDaLeggere= "rubrica1.csv";
		String fileDaScrivere="rubricaMOD.txt";
		ListaContatti rub1= new ListaContatti();
		try {
			BufferedReader lettore= new BufferedReader(new FileReader(percorsoFile + fileDaLeggere));
			PrintStream scrittore= new PrintStream(new FileOutputStream(percorsoFile + fileDaScrivere));
			while((riga= lettore.readLine()) != null) {
				// codice
				String[] contatto = riga.split(";");
				rub1.caricaLista(contatto[0],contatto[1],contatto[2],contatto[3]);
			}
			 
				scrittore.println(rub1.toString());
				System.out.println("fatto");
			
			//chiusura
			lettore.close();
			scrittore.close();
		}
		catch (IOException e) {
			System.out.println("Errore di input/output: " + e);
		}

	}

}
