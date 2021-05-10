package it.beije.makemake.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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




public class FileUtil {

	private static BufferedReader openCsvToRead(String path) throws FileNotFoundException {
		File file = new File(path);
		FileReader reader = new FileReader(file);
		BufferedReader buffer = new BufferedReader(reader);
		
		return buffer;
	}

	public static void addContactListToCsv(String path, List<Contatto> contactList) throws IOException{
		File file = new File(path);
		FileWriter writer = new FileWriter(file, true);
		
		for(Contatto contatto : contactList) {
			writer.write(contatto.getId());
			writer.write(';');
			writer.write(contatto.getNome());
			writer.write(';');
			writer.write(contatto.getCognome());
			writer.write(';');
			writer.write(contatto.getTelefono());
			writer.write(';');
			writer.write(contatto.getEmail());
			writer.write('\n');
			
		}
		
		writer.flush();
		writer.close();
		
	}

	public static void addContactToCsv(String path, Contatto contatto) throws IOException{
		File file = new File(path);
		FileWriter writer = new FileWriter(file, true);
		
		writer.write(contatto.getId());
		writer.write(';');
		writer.write(contatto.getNome());
		writer.write(';');
		writer.write(contatto.getCognome());
		writer.write(';');
		writer.write(contatto.getTelefono());
		writer.write(';');
		writer.write(contatto.getEmail());
		writer.write('\n');
		
		writer.flush();
		writer.close();
		
	}

	public static void createCsv(String path,  List<Contatto> list) throws IOException {
		File file = new File(path);
		FileWriter writer = new FileWriter(file);
		
		writer.write("NOME;COGNOME;TELEFONO;EMAIL;\n");
		
		for(Contatto contatto : list) {
			writer.write(contatto.getNome());
			writer.write(';');
			writer.write(contatto.getCognome());
			writer.write(';');
			writer.write(contatto.getTelefono());
			writer.write(';');
			writer.write(contatto.getEmail());
			writer.write('\n');
		}
		
		writer.flush();
		writer.close();
	}

	public static List<Contatto> convertCsvToList(String path) throws IOException{
		
		BufferedReader bufferedReader = openCsvToRead(path);
		List<Contatto> contatti = new ArrayList<>();
		
		while(bufferedReader.ready()) {
			String line = bufferedReader.readLine();
			String values[] = line.split(";");
			contatti.add(new Contatto(Integer.parseInt(values[0]), values[1], values[2], values[3], values[4] ));
		}
		bufferedReader.close();

		return contatti;
	}

	

	public static Document openXmlToRead(String path) throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		File file = new File(path);
		
		Document document = builder.parse(file);
	
		return document;
		
	}

	public static void addContactToXml(String path, Contatto contatto){
		//non uso il fileWriter o Reader ma mi creo un oggetto document
		try {
			
			Document oldDocument = openXmlToRead(path);

			
			List<Contatto> contactList = convertXmlToList(oldDocument);
			
			contactList.add(contatto);
			
			createXml(path, contactList);
		
		}catch(Exception e) {
			e.getStackTrace();
		}
				
	}
	
	public static void addContactListToXml(String path, List<Contatto> contatti){
		try {
			
			Document oldDocument = openXmlToRead(path);
			

			List<Contatto> contactList = convertXmlToList(oldDocument);
						
			for(Contatto cont : contatti) {
				contactList.add(cont);
			}
			
			createXml(path, contactList);
		
		}catch(Exception e) {
			e.getStackTrace();
		}
	}
	
	public static List<Contatto> convertXmlToList(Document doc) {
		//estraggo il root tag
		Element rubrica = doc.getDocumentElement();
		//estraggo tag figli di tipo contatto del tag rubrica
		NodeList contactListTag = rubrica.getElementsByTagName("contatto");
		//creo una lista in cui salvare tutti i contatti estratti
		List<Contatto> contactList = new ArrayList<>();
		Element contactNode = null;
		
		
		for(int i=0 ; i<contactListTag.getLength(); i++) {
			contactNode = (Element) contactListTag.item(i);
			
			Element tagId = (Element) contactNode.getElementsByTagName("id").item(0);
			Element tagName = (Element) contactNode.getElementsByTagName("nome").item(0);
			Element tagSurname = (Element) contactNode.getElementsByTagName("cognome").item(0);
			Element tagTelef = (Element) contactNode.getElementsByTagName("telefono").item(0);
			Element tagEmail = (Element) contactNode.getElementsByTagName("email").item(0);
			
			contactList.add(new Contatto( Integer.parseInt(tagId.getTextContent()),
										  tagName.getTextContent(),
										  tagSurname.getTextContent(),
										  tagTelef.getTextContent(),
										  tagEmail.getTextContent()
										)
							);
		}
		return contactList;
	}
	
	public static void createXml(String path, List<Contatto> contatti) throws Exception{

		try {
			Document newDocument = createDocument(contatti);
			StreamResult result = new StreamResult(new File(path));
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			DOMSource source = new DOMSource(newDocument);
			
			transformer.transform(source, result);
			System.out.println("File saved!");
		}catch(Exception e) {
			e.getStackTrace();
		}
	}
	
	private static Element createDocumentElement(Contatto cont, Document document) {
		Element contatto = document.createElement("contatto");
		
		Element id = document.createElement("id");
		Element nome = document.createElement("nome");
		Element cognome = document.createElement("cognome");
		Element telefono = document.createElement("telefono");
		Element email = document.createElement("email");
		
		id.setTextContent(Integer.toString(cont.getId()));
		nome.setTextContent(cont.getNome());
		cognome.setTextContent(cont.getCognome());
		telefono.setTextContent(cont.getTelefono());
		email.setTextContent(cont.getEmail());
		
		contatto.appendChild(id);
		contatto.appendChild(nome);
		contatto.appendChild(cognome);
		contatto.appendChild(telefono);
		contatto.appendChild(email);
		
		return contatto;
	}
	
	private static Document createDocument(List<Contatto> contactList) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.newDocument();
		
		Element rootTag = document.createElement("rubrica");
		
		for(Contatto cont : contactList) {;
			Element contatto = createDocumentElement(cont, document);
			rootTag.appendChild(contatto);
		}
		
		document.appendChild(rootTag);
		
		return document;
	}
}
