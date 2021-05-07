package it.andrea.makemake.contatti.entity;

import java.util.List;

public class ContattoUtils {
	public static void printList(List<Contatto> contatti) {
		int index = 1;
		for (Contatto contatto : contatti) {
			System.out.println(index++ + ") " + contatto + "\n");
		}
	}
}
