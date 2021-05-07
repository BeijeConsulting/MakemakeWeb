package it.beije.makemake;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {
	
	public static List<String> readRubrica(String pathFile) throws IOException {
		
		FileReader fileReader = new FileReader(pathFile);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<String> rows = new ArrayList<String>();
		while (bufferedReader.ready()) {
			String line = bufferedReader.readLine();
			System.out.println(line);
			rows.add(line);
		}
		fileReader.close();
		
		return rows;
	}

}
