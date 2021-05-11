package rubrica;

import java.io.File;

public class Test {

public static void main(String[] args) throws Exception {
		
		ListaContatti a = CsvManager.ListaDaCsv(new File("C:\\Users\\Padawan05\\Desktop\\FileJava\\rubeicaCSV2.csv")); 
		a.caricaLista("GIACOMO","rossi","334 556 7892","rossig@gmail.com");
		CsvManager.CsvDaLista(a, new File("C:\\Users\\Padawan05\\Desktop\\FileJava\\rubeicaCSV.csv"));
		
		System.out.println("fatto csv");
		a.caricaLista("Beppe","Bazzi","354 566 7796","bazzibeppe@gmail.com");
		XmlManager.xmlDaLista(a, new File("C:\\Users\\Padawan05\\Desktop\\FileJava\\rubeicaXML2.xml"));
		System.out.println("fatto xml");
		CsvManager.CsvDaLista(a, new File("C:\\Users\\Padawan05\\Desktop\\FileJava\\rubeicaCSV2.csv"));
		
	}

}
