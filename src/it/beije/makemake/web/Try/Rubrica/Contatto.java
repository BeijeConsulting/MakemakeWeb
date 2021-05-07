package it.beije.makemake.web.Try.Rubrica;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rubrica")
public class Contatto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name = "nome")
	private String nome;

	@Column(name = "cognome")
	private String cognome;

	@Column(name = "telefono")
	private String telefono;

	@Column(name = "email")
	private String email;
	
	public Contatto() {}
	
	public Contatto(String nome, String cognome,String telefono,String email) {
		this.nome=nome;
		this.cognome=cognome;
		this.telefono=telefono;
		this.email=email;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder()
				.append("[Id :").append(id).append(", ")
				.append("Nome : ").append(nome).append(", ")
				.append("Cognome : ").append(cognome).append(", ")
				.append("Telefono : ").append(telefono).append(", ")
				.append("Email : ").append(email).append("] ");

		
		return builder.toString();
	}
	
	public boolean equals(Contatto c1, Contatto c2) {
		if(c1.nome==c2.nome)
			if(c1.cognome==c2.nome)
				if(c1.email==c2.email)
					if(c1.telefono==c2.telefono)
					return true;
		
		return false;
	}
}
