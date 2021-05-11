package it.andrea.makemake.web.controller;

import javax.persistence.PersistenceException;

import it.andrea.makemake.web.entity.Product;
import it.andrea.makemake.web.entity.User;
import it.andrea.makemake.web.model.DBManager;

public class EcommerceController {
	public static DBManager dbManager = new DBManager();
	public static final String MISSING_USERNAME_OR_PW = "Errore: username e password sono obbligatorie";
	public static final String USER_ALREADY_EXISTS = "Errore: Un utente con questa username è già registrato";
	public static final String OK = "Operazione completata con successo";

	public static User login(String username, String password) {
		return dbManager.getUserByUsernameAndPassword(username, password);
	}

	public static String register(User user) {
		if (user.getUsername() == null || user.getUsername().trim().isEmpty() || user.getPassword() == null
				|| user.getPassword().trim().isEmpty()) {
			return MISSING_USERNAME_OR_PW;
		} else {
			try {
				dbManager.addUser(user);
				return OK;
			} catch (PersistenceException userExists) {
				return USER_ALREADY_EXISTS;
			}
		}
	}
	
	public static String modify(User oldUser, User newUser) {
		if (newUser.getUsername() == null || newUser.getUsername().trim().isEmpty() || newUser.getPassword() == null
				|| newUser.getPassword().trim().isEmpty()) {
			return MISSING_USERNAME_OR_PW;
		} else {
			try {
				dbManager.modifyUser(oldUser, newUser);
				return OK;
			} catch (PersistenceException userExists) {
				return USER_ALREADY_EXISTS;
			}
		}
	}
	
	public static Product getProductById(int id) {
		return dbManager.getProductById(id);
	}
}
