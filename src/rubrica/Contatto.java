
package rubrica;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "rubrica")
public class Contatto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRubrica")
	private int id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "cognome")
	private String cognome;

	@Column(name = "telefono")
	private String telefono;

	@Column(name = "mail")
	private String mail;

	// getter & setter

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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	// metodi
	@Override
	public boolean equals(Object anObject) {
		boolean uguale = false;
		if (anObject instanceof Contatto) {
			Contatto c2 = (Contatto) anObject;
			if ((this.getNome().equalsIgnoreCase(c2.getNome())) && (this.getCognome().equalsIgnoreCase(c2.getCognome()))
					&& (this.getTelefono().equalsIgnoreCase(c2.getTelefono()))
					&& (this.getMail().equalsIgnoreCase(c2.getMail()))

			) {
				uguale = true;
			}
		}
		return uguale;
	}

	@Override
	public String toString() {
		return "Contatto [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", telefono=" + telefono + ", mail="
				+ mail + "]";
	}

	// costruttore
	public Contatto(int id, String nome, String cognome, String telefono, String mail) {
		this.setId(id);
		this.setNome(nome);
		this.setCognome(cognome);
		this.setTelefono(telefono);
		this.setMail(mail);
	}

	public Contatto(String nome, String cognome, String telefono, String mail) {
		this(0, nome, cognome, telefono, mail);
	}

	public Contatto() {
	}
}

