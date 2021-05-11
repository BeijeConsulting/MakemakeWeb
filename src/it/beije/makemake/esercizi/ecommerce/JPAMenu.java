package it.beije.makemake.esercizi.ecommerce;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Criteria;
import org.hibernate.Session;

public class JPAMenu {
	static Scanner tastiera = new Scanner(System.in);

	public static void main(String[] args) {

		boolean flag = true;

		while (flag) {
			menu();
			int scelta = tastiera.nextInt();

//		tastiera.nextLine(); Pulisco la linea

			switch (scelta) {

			default:
				System.out.println("ERRORE!!!");
				System.out.println("INSERISCI UN NUMERO VALIDO!!!");
				break;

			case 1:
				printUser();
				break;

			case 2:
				printProduct();
				break;

			case 3:
				JPAManager.getOrderInfo();
				break;

			case 4:

				break;

			case 9:
				flag = false;
				break;

			}

		}

	}

	public static void menu() {

		System.out.println("__________MENU__________");
		System.out.println("1) Stampa Utenti");
		System.out.println("2) Stampa Prodotti");
		System.out.println("3) Stampa dettaglio ordine");
		System.out.println("4) Crea un ordine");
		System.out.println("9) Exit");
		System.out.println("__________MENU__________");

	}

	public static void printUser() {

		for (User u : JPAManager.getUser())
			System.out.println(u);

	}

	public static void printProduct() {

		for (Product p : JPAManager.getProduct())
			System.out.println(p);

	}

	public static User logIn() {

		boolean flag = true;
		Scanner tastiera = new Scanner(System.in);
		String username;
		User user=null;

		while (flag) {
			username = tastiera.nextLine();
			user = JPAManager.getUserByUsername(username);
			if (user != null) {
				flag = false;
			}

		}
		
		return user;
	}
}
