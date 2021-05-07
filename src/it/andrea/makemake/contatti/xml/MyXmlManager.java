package it.andrea.makemake.contatti.xml;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import it.andrea.makemake.contatti.csv.CsvManager;
import it.andrea.makemake.contatti.entity.Contatto;

public class MyXmlManager {
	public final static String XML_PATH = "C:/Users/Padawan10/git/Makemake/src/it/andrea/esercitazione/xml/xmlfiles/rubrica.xml";
	public final static String NEW_XML_PATH = "C:/Users/Padawan10/git/Makemake/src/it/andrea/esercitazione/xml/xmlfiles/new_rubrica.xml";
	public final static String CSV_PATH = "C:/Users/Padawan10/git/Makemake/src/it/andrea/esercitazione/contatti/csvfiles/rubrica1.csv";
	public final static String NEW_CSV_PATH = "C:/Users/Padawan10/git/Makemake/src/it/andrea/esercitazione/contatti/csvfiles/new_rubrica.csv";

	public static void printFile(File f) throws Exception {
		FileReader fileReader = new FileReader(f);
		while (fileReader.ready()) {
			System.out.print((char) fileReader.read());
		}
		fileReader.close();
	}

	public static List<Contatto> retrieveContactList(File f) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(f);

		Element rubrica = document.getDocumentElement();
		NodeList contatti = rubrica.getElementsByTagName("contatto");

		List<Contatto> contactList = new ArrayList<Contatto>();
		Element contatto;
		for (int i = 0; i < contatti.getLength(); i++) {
			contatto = (Element) contatti.item(i);
			Element nome = (Element) contatto.getElementsByTagName("nome").item(0);
			Element cognome = (Element) contatto.getElementsByTagName("cognome").item(0);
			Element telefono = (Element) contatto.getElementsByTagName("telefono").item(0);
			Element email = (Element) contatto.getElementsByTagName("email").item(0);
			Contatto contact = new Contatto(nome.getTextContent(), cognome.getTextContent(), telefono.getTextContent(),
					email == null ? null : email.getTextContent());
			contactList.add(contact);
		}
		return contactList;
	}

	public static Element convertToXml(Object object) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.newDocument();

		Element objectElement = document.createElement(object.getClass().getName().toLowerCase());

		return objectElement;
	}

	public static void writeXmlFile(List<Contatto> contactList) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.newDocument();

		Element root = document.createElement("contatti");
		for (Contatto contatto : contactList) {
			root.appendChild(contatto.toXmlElement());
		}
		document.appendChild(root);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);

		StreamResult result = new StreamResult(new File(NEW_XML_PATH));

		StreamResult syso = new StreamResult(System.out);

		transformer.transform(source, result);
		transformer.transform(source, syso);

		System.out.println("\nFile saved!");
	}

	public static void main(String[] args) throws Exception {
		File f = new File(XML_PATH);
		if (f.exists()) {
			printFile(f);
			System.out.println("\n-------------------\n\n");

			CsvManager.printContactList(retrieveContactList(f));

			writeXmlFile(retrieveContactList(f));
		}
	}
}
