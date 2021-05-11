package rubrica;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rubrica")
public class ContattoAnnotation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name = "nome")
	private String nome;

	@Column(name = "cognome")
	private String cognome;

	public ContattoAnnotation() {}
	
	public ContattoAnnotation( String cognome, String nome, String telefono, String email) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
		this.email = email;
	}

	@Column(name = "telefono")
	private String telefono;

	@Column(name = "email")
	private String email;
	
	
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
				.append("{ id : ").append(id).append(", ")
				.append("nome : ").append(nome).append(", ")
				.append("cognome : ").append(cognome).append(", ")
				.append("telefono : ").append(telefono).append(", ")
				.append("email : ").append(email).append(" }");
		
		return builder.toString();
	}
	
	
	
	public boolean equals(Object obj) {
		if(obj instanceof ContattoAnnotation) {
			ContattoAnnotation c = (ContattoAnnotation) obj;
			return nome.equals(c.nome) && cognome.equals(c.cognome) && telefono.equals(c.telefono) && email.equals(c.email);
		}
		return false;
		}
}
