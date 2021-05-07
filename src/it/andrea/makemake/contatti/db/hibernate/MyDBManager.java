package it.andrea.makemake.contatti.db.hibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.andrea.makemake.contatti.entity.Contatto;

public class MyDBManager {

	public static void update(Connection connection, String filter, String oldValue, String newValue) {
		Statement statement = null;
		try {
			statement = connection.createStatement();
			int r = statement.executeUpdate(
					"UPDATE rubrica set " + filter + " = '" + newValue + "' WHERE " + filter + " = '" + oldValue + "'");
			System.out.println("Record modificati: " + r);

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void insert(Connection connection, Contatto contatto) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement("INSERT INTO rubrica (cognome,nome,telefono,email) VALUES (?,?,?,?)");
			preparedStatement.setString(1, contatto.getCognome());
			preparedStatement.setString(2, contatto.getNome());
			preparedStatement.setString(3, contatto.getTelefono());
			preparedStatement.setString(4, contatto.getEmail());
			preparedStatement.executeUpdate();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void insert(Connection connection, List<Contatto> contatti) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement("INSERT INTO rubrica (cognome,nome,telefono,email) VALUES (?,?,?,?)");
			for (Contatto contatto : contatti) {
				preparedStatement.setString(1, contatto.getCognome());
				preparedStatement.setString(2, contatto.getNome());
				preparedStatement.setString(3, contatto.getTelefono());
				preparedStatement.setString(4, contatto.getEmail());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static List<Contatto> searchBy(Connection connection, String filter, String value) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Contatto> contatti = new ArrayList<Contatto>();
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM rubrica WHERE " + filter + " = ?");
			preparedStatement.setString(1, value);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				contatti.add(new Contatto(resultSet.getString("nome"), resultSet.getString("cognome"),
						resultSet.getString("telefono"), resultSet.getString("email")));
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return contatti;
	}
	
	public static List<Contatto> getRubrica(Connection connection) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Contatto> contatti = new ArrayList<Contatto>();
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM rubrica");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				contatti.add(new Contatto(resultSet.getString("nome"), resultSet.getString("cognome"),
						resultSet.getString("telefono"), resultSet.getString("email")));
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return contatti;
	}

	public static void deleteBy(Connection connection, String filter, String value) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement("DELETE FROM rubrica WHERE " + filter + " = ?");
			preparedStatement.setString(1, value);
			preparedStatement.executeUpdate();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void clear(Connection connection) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement("DELETE FROM rubrica");
			preparedStatement.executeUpdate();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/makemake?serverTimezone=CET",
				"root", "Beije10");
//		insert(connection, MyCsvManager.getContactList(new File(MyCsvManager.DEST_DIR)));
//		clear(connection);
		connection.close();
	}

}
