package it.beije.makemake.web.myEcomm;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.exception.ConstraintViolationException;

import it.beije.makemake.web.manager.singleton.*;
import it.beije.makemake.web.myEcomm.entity.Product;
import it.beije.makemake.web.myEcomm.entity.User;
public class Controller {
//classe in cui chiamare tutti i metodi 
	
	private static Manager util = new Manager();
	public static final String MISSING_USERNAME_OR_PW = "Errore username e password sono obbligatorie";
	public static final String USER_ALREADY_EXISTS = "Un utente con questo username � gi� registrato";
	public static final String OK = "Operazione completata con successo";
	
	public static User login(String username,String password) {
		return util.getUserByUsernameAndPassword(username, password);
	}
	
	public static String  register(User user) {
		if(user.getUsername()==null || user.getUsername().trim().isEmpty() || user.getPassword() == null || user.getPassword().trim().isEmpty()) {
			return MISSING_USERNAME_OR_PW;
		}else {
			try {
				util.addUser(user);
				return OK;
			}catch (PersistenceException alreadyExists) {
				return USER_ALREADY_EXISTS;
			}
		}
	}
	
	public static Product getProductbyID(Integer id) {
		Product p =  util.getProductbyId(id);
		return p;
	}

	public static String modificaUtente(User oldUser, User newUser) {
		if(newUser.getUsername()==null || newUser.getUsername().trim().isEmpty() || newUser.getPassword() == null || newUser.getPassword().trim().isEmpty()) {
			return MISSING_USERNAME_OR_PW;
		}else {
			try {
				util.editUser(oldUser,newUser);
				return OK;
			}catch (PersistenceException alreadyExists) {
				return USER_ALREADY_EXISTS;
			}
		}
	}
}
