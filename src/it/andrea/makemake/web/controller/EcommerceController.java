package it.andrea.makemake.web.controller;

import it.andrea.makemake.web.entity.User;
import it.andrea.makemake.web.model.DBManager;

public class EcommerceController {
	public static DBManager dbManager = new DBManager();
	
	public static User login(String username, String password) {
		return dbManager.getUserByUsernameAndPassword(username, password);
	}
}
