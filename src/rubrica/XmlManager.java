package rubrica;

import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.PrintStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlManager {

	public static void main(String[] args) {
		File file = new File("C:\\Users\\Padawan05\\Desktop\\FileJava\\rubrica.xml");
		String percorsoFile = "C:\\Users\\Padawan05\\Desktop\\FileJava\\";

		String fileDaScrivere = "rubricaMODXML.txt";
		ListaContatti rub1 = new ListaContatti();
		try {

			PrintStream scrittore = new PrintStream(new FileOutputStream(percorsoFile + fileDaScrivere));
			rub1 = listaDaXml(file);

			scrittore.println(rub1.toString());
			xmlDaLista(rub1, new File("C:\\Users\\Padawan05\\Desktop\\FileJava\\rubeicaXML1.xml"));
			System.out.println("fatto");

			scrittore.close();
		} catch (Exception e) {
			System.out.println("Errore di input/output: " + e);
		}

	}

	public static ListaContatti listaDaXml(File file) throws ParserConfigurationException, SAXException, IOException {
		ListaContatti lista = new ListaContatti();
		// File file = new File("C:\\Users\\Padawan05\\Desktop\\FileJava\\rubrica.xml");
		if (file.exists()) {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);
			Element rubrica = document.getDocumentElement();
			NodeList contatti = rubrica.getElementsByTagName("contatto");

			Element contatto;
			for (int i = 0; i < contatti.getLength(); i++) {
				contatto = (Element) contatti.item(i);

				Element nome = (Element) contatto.getElementsByTagName("nome").item(0);
				Element cognome = (Element) contatto.getElementsByTagName("cognome").item(0);
				Element telefono = (Element) contatto.getElementsByTagName("telefono").item(0);

				lista.caricaLista(nome.getTextContent(), cognome.getTextContent(), telefono.getTextContent(),
						"no mail");

			}

			return lista;
		} else
			return null;

	}

	public static void xmlDaLista(ListaContatti lista, File f) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.newDocument();

		Element root = document.createElement("contatti");
		for (int i = 0; i < lista.getLista().size(); i++) {

			Element contatto = document.createElement("contatto");

			Element nome = document.createElement("nome");
			nome.setTextContent(lista.getLista().get(i).getNome());
			Element cognome = document.createElement("cognome");
			cognome.setTextContent(lista.getLista().get(i).getCognome());
			Element telefono = document.createElement("telefono");
			telefono.setTextContent(lista.getLista().get(i).getTelefono());
			Element mail = document.createElement("mail");
			mail.setTextContent(lista.getLista().get(i).getMail());

			contatto.appendChild(nome);
			contatto.appendChild(cognome);
			contatto.appendChild(telefono);

			root.appendChild(contatto);
		}

		document.appendChild(root);

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);

		StreamResult result = new StreamResult(new File(f.getPath()));

		// Output to console for testing
		StreamResult syso = new StreamResult(System.out);

		transformer.transform(source, result);
		transformer.transform(source, syso);

		System.out.println("File saved!");
	}

}
