package it.andrea.makemake.contatti.db.hibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dosser {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		List<Connection> connectionList = new ArrayList<Connection>();

		try {
			while (true) {
				connectionList.add(DriverManager
						.getConnection("jdbc:mysql://localhost:3306/makemake?serverTimezone=CET", "root", "Beije10"));
			}
		} catch (SQLException e) {
			System.out.println("Crashed at " + connectionList.size());
			e.printStackTrace();
		} finally {
			for (Connection connection : connectionList) {
				connection.close();
			}
		}
	}

}
