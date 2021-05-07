package it.andrea.makemake.web.controller;

import java.util.List;

import it.andrea.makemake.contatti.db.jpa.JpaManager;
import it.andrea.makemake.contatti.entity.Contatto;

public class Controller {
	private static JpaManager jpaManager = new JpaManager();
	
	public void addContatto(String nome, String cognome, String telefono, String email) {
		jpaManager.insert(new Contatto(nome, cognome, telefono, email));
	}
	
	public List<Contatto> getAllContatti(){
		return jpaManager.selectAll();
	}
	
//	--------------------------------------------------------------------------------------
	
	
}
