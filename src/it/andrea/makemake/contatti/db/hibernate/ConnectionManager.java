package it.andrea.makemake.contatti.db.hibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionManager {

	private static List<Connection> connectionList = new ArrayList<Connection>();
	private static final int CONNECTION_LIMIT = 10;

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
//		TODO
		
		for (Connection connection : connectionList) {
			if (connection.isClosed()) {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/makemake?serverTimezone=CET",
						"root", "Beije10");
				return connection;
			}
		}
		if (connectionList.size() < CONNECTION_LIMIT) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connectionList.add(DriverManager.getConnection("jdbc:mysql://localhost:3306/makemake?serverTimezone=CET",
					"root", "Beije10"));
			return connectionList.get(connectionList.size() - 1);
		}
		return null;
	}
}
