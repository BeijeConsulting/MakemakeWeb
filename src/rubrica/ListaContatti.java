
package rubrica;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaContatti {

	// Attributi
	private ArrayList<Contatto> lista = new ArrayList<Contatto>();

	// metodi
	public ArrayList<Contatto> getLista() {
		return lista;
	}

	public void caricaLista(Contatto obj) {
		if (!contattoEsistente(obj)) {
			lista.add(obj);
		}
	}

	public void caricaLista(int id, String nome, String cognome, String telefono, String mail) {

		Contatto obj = new Contatto(id, nome, cognome, telefono, mail);
		if (!contattoEsistente(obj)) {
			lista.add(obj);
		}
	}

	public void caricaLista(String nome, String cognome, String telefono, String mail) {

		Contatto obj = new Contatto(nome, cognome, telefono, mail);
		if (!contattoEsistente(obj)) {
			lista.add(obj);
		}
	}

	public boolean contattoEsistente(Contatto c) {
		boolean esiste = false;
		for (int i = 0; i < this.lista.size(); i++) {
			if (this.lista.get(i).equals(c)) {
				esiste = true;
			}
		}
		return esiste;
	}

	public boolean contattoEsistente(String nome, String cognome) {
		boolean esiste = false;
		for (Contatto c : lista) {
			if (c.getNome().equalsIgnoreCase(nome)  && c.getCognome().equalsIgnoreCase(cognome) ) {
				esiste = true;
			}
		}
		return esiste;
	}

	@Override
	public String toString() {
		String s = "ListaContatti [" + "\n";
		for (int i = 0; i < lista.size(); i++) {
			s += "\t\t " + lista.get(i).toString() + "\n";
		}
		s += "\t\t]";
		return s;
	}

	public Contatto ricercaContatto(String nome, String cognome, String telefono, String mail) {
		Contatto con = new Contatto(nome, cognome, telefono, mail);
		for (int i = 0; i < this.getLista().size(); i++) {
			if (con.equals(this.getLista().get(i))) {
				return this.getLista().get(i);
			}
		}

		return null;
	}

	public int posContatto(Contatto con) {

		for (int i = 0; i < this.getLista().size(); i++) {
			if (con.equals(this.getLista().get(i))) {
				return i;
			}
		}

		return -1;
	}

	public int posContatto(String nome, String cognome) {

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getNome().equalsIgnoreCase(nome)  && lista.get(i).getCognome().equalsIgnoreCase(cognome)) {
				return i;
			}
		}
		return -1;
	}

	public void modificaContatto(int pos, Contatto nuovo) {
		this.getLista().set(pos, nuovo);

	}

	public void fondiLista(ListaContatti nLista) {
		for (int i = 0; i < nLista.getLista().size(); i++) {
			this.caricaLista(nLista.getLista().get(i));
		}
	}

//	public ArrayList<Contatto> cercaContatto(int parametro, String valoreParametro){
//		ArrayList<Contatto> a = new ArrayList<Contatto>();
//		for (int i = 0; i <this.getLista().size(); i++) {
//			Contatto contattoCorrente = this.getLista().get(i);
//			switch(parametro) {
//				case 1 :                          
//					if(contattoCorrente.getNome().equalsIgnoreCase(valoreParametro))
//						a.add(contattoCorrente);
//					break;
//				case 2 :
//					if(contattoCorrente.getCognome().equalsIgnoreCase(valoreParametro))
//						a.add(contattoCorrente);
//					break;	
//				case 3 :
//					if(contattoCorrente.getTelefono().equalsIgnoreCase(valoreParametro))
//						a.add(contattoCorrente);
//					break;
//				case 4 :
//					if(contattoCorrente.getMail().equalsIgnoreCase(valoreParametro))
//						a.add(contattoCorrente);
//					break;
//				default:
//					System.out.println("Parametro di ricerca non valido");
//			}
//		}
//		
//		return a;

//	}

}

