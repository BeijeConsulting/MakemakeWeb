package rubrica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class RubricaDigitale {

	static Scanner tastiera = new Scanner(System.in);
	static String percorsoFile = "C:\\Users\\Padawan05\\Desktop\\FileJava\\";

	public static void main(String[] args) throws Exception {

		String riga;

		
		String fileDaLeggere = "rubrica1.csv";
		String fileDaScrivere = "rubricaMOD.txt";
		ListaContatti listarubrica = new ListaContatti();
		try {
			BufferedReader lettore = new BufferedReader(new FileReader(percorsoFile + fileDaLeggere));
			PrintStream scrittore = new PrintStream(new FileOutputStream(percorsoFile + fileDaScrivere));
			while ((riga = lettore.readLine()) != null) {
				// codice
				String[] contatto = riga.split(";");
				listarubrica.caricaLista(contatto[0], contatto[1], contatto[2], contatto[3]);
			}

			scrittore.println(listarubrica.toString());
			System.out.println("fatto");

			// chiusura
			lettore.close();
			scrittore.close();
		} catch (IOException e) {
			System.out.println("Errore di input/output: " + e);
		}

		boolean acceso = true;

	//	ListaContatti lista = new ListaContatti();

		while (acceso) {
			menu();
			System.out.println("Scegli un opzione:...");

			int scelta = tastiera.nextInt();
			tastiera.nextLine();

			switch (scelta) {
			case 0:
				acceso = false;

				System.out.println("chiusura programma in corso.");
				System.out.println("chiusura programma in corso..");
				System.out.println("chiusura programma in corso...");
				break;

			case 1:
				String[] contatto = creaContatto();
				listarubrica.caricaLista(contatto[0], contatto[1], contatto[2], contatto[3]);
				System.out.println("Contatto caricato in rubrica");
				break;
			case 2:
				System.out.println("Percorso di Default: [ " + percorsoFile + " ]");
				System.out.println();
				System.out.println("vuoi cambiarlo? [SI]   [NO]");
				String cambio = tastiera.nextLine();
						if(cambio.equalsIgnoreCase("si")) {
							System.out.println("inserisci in nuovo percorso in cui salvare i file:");
							String nPercorso = tastiera.nextLine();
							if(new File(nPercorso).isDirectory()) {
								percorsoFile= nPercorso;
							}else
								System.out.println("La cartella non esiste.");
						}
				
				
				break;
			case 3:
				ListaContatti nuova = caricaDaFile();
				System.out.println("vuoi sovrascrivere la vecchia lista? [SI] [NO]");
				String opzione = tastiera.nextLine();
				if(opzione.equalsIgnoreCase("si")) {
					listarubrica= nuova;
					System.out.println("fatto");
				}
				System.out.println("vuoi fondere la vecchia lista con la nuova?[SI] [NO]");
				opzione = tastiera.nextLine();
				if(opzione.equalsIgnoreCase("si")) {
					listarubrica.fondiLista(nuova);
					System.out.println("fatto");
				}
				break;
			case 4:
				System.out.println(listarubrica);
				break;

			}
		}

	}

	public static void menu() {
		System.out.println();
		System.out.println("[-------------Menù-----------------------]");
		System.out.println("[1)Crea un contatto.                     ]");
		System.out.println("[2)Visualizza/Cambia cartella di default.]");
		System.out.println("[3)Carica lista da Xml o Csv.            ]");
		System.out.println("[4)visualizza lista.                     ]");
		System.out.println("[0)Esci.                                 ]");
		System.out.println("[----------------------------------------]");
		System.out.println();
	}

	public static String[] creaContatto() {
		String[] contatto = new String[4];
		System.out.println("inserisci i dati del contatto:");
		System.out.println();

		System.out.println("Nome: ");
		contatto[0] = tastiera.nextLine();

		System.out.println();
		System.out.println("Cognome: ");
		contatto[1] = tastiera.nextLine();

		System.out.println();
		System.out.println("Telefono: ");
		contatto[2] = tastiera.nextLine();

		System.out.println();
		System.out.println("Mail: ");
		contatto[3] = tastiera.nextLine();
		System.out.println();

		return contatto;
	}

	public static ListaContatti caricaDaFile() throws ParserConfigurationException, SAXException, IOException {
		System.out.println("caricare la lista da file: [CSV] o [XML]");
		String tipoFile = tastiera.nextLine();
		ListaContatti rub = null	;
			if(tipoFile.equalsIgnoreCase("xml")){
				System.out.println("inserisci il nome del file:");
				 String nomeFile = tastiera.nextLine() +".xml";
				 File a = new File(percorsoFile + nomeFile);
				 if(a.exists()) {
					 rub = XmlManager.listaDaXml(a );
				 }else
					 System.out.println("file non esiste");
				
			}else if(tipoFile.equalsIgnoreCase("csv")){
				System.out.println("inserisci il nome del file:");
				 String nomeFile = tastiera.nextLine() +".csv";
				 File a = new File(percorsoFile + nomeFile);
				 if(a.exists()) {
					 rub = CsvManager.ListaDaCsv(a);
				 }else
					 System.out.println("file non esiste");
				
			}else
				System.out.println("formato non valido.");
			
		return rub;
	}
	
}
