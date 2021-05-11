package rubrica;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CsvManager {

	public static void CsvDaLista(ListaContatti contatti, File f) throws Exception {
		FileWriter writer = new FileWriter(f, true);
		for (Contatto contatto : contatti.getLista()) {
			writer.write(contatto.getCognome());
			writer.write(';');
			writer.write(contatto.getNome());
			writer.write(';');
			writer.write(contatto.getTelefono());
			writer.write(';');
			writer.write(contatto.getMail());
			writer.write('\n');
		}

		writer.flush();
		writer.close();
	}

	public static ListaContatti ListaDaCsv(File f) throws IOException {
		// File f = new File("C:/temp/new_prova.txt");

		ListaContatti contatti = new ListaContatti();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
		while (bufferedReader.ready()) {
			String row = bufferedReader.readLine();
			String[] rowParts = row.split(";");

			Contatto contatto = new Contatto();
			contatto.setNome(rowParts[0]);
			contatto.setCognome(rowParts[1]);
			contatto.setTelefono(rowParts[2]);
			contatto.setMail(rowParts[3]);

			contatti.caricaLista(contatto);

		}

		bufferedReader.close();
		return contatti;
	}

}
