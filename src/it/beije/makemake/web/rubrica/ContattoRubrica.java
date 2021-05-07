package it.beije.makemake.web.rubrica;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

@Entity
@Table(name = "rubrica")
public class ContattoRubrica {
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
	
	public ContattoRubrica() {
	}
	
	public ContattoRubrica(String nome, String cognome, String telefono, String email) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
		this.email = email.trim().equals("") ? null : email;
	}

	public ContattoRubrica(String[] contattoCsv) {
		this.nome = contattoCsv[0];
		this.cognome = contattoCsv[1];
		this.telefono = contattoCsv[2];
		if (contattoCsv.length == 4) {
			this.email = contattoCsv[3];
		}
	}

	public ContattoRubrica(ContattoRubrica contatto) {
		super();
		this.nome = contatto.getNome();
		this.cognome = contatto.getCognome();
		this.telefono = contatto.getTelefono();
		this.email = contatto.getEmail() == null ? null : email;
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
//	
//	public String toString() {
//		StringBuilder builder = new StringBuilder()
//				.append("{ id : ").append(id).append(", ")
//				.append("nome : ").append(nome).append(", ")
//				.append("cognome : ").append(cognome).append(", ")
//				.append("telefono : ").append(telefono).append(", ")
//				.append("email : ").append(email).append(" }");
//		
//		return builder.toString();
//	}
	@Override
	public String toString() {
		return "Contatto [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", telefono=" + telefono + ", email="
				+ email + "]";
	}

	public String toCsv() {
		return this.getNome() + ";" + this.getCognome() + ";" + this.getTelefono() + ";" + this.getEmail();
	}
	
	
	public boolean equalsNoOptional(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContattoRubrica other = (ContattoRubrica) obj;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
	}
	public Element toXmlElement() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.newDocument();

		Element contatto = document.createElement("contatto");
//		if (this.getEta() != null) {
//			contatto.setAttribute("eta", Integer.toString(this.getEta()));
//		}
		if (this.getNome() != null) {
			Element nome = document.createElement("nome");
			nome.setTextContent(this.getNome());
			contatto.appendChild(nome);
		}
		if (this.getCognome() != null) {
			Element cognome = document.createElement("cognome");
			cognome.setTextContent(this.getCognome());
			contatto.appendChild(cognome);
		}
		if (this.getTelefono() != null) {
			Element telefono = document.createElement("telefono");
			telefono.setTextContent(this.getTelefono());
			contatto.appendChild(telefono);
		}
		if (this.getEmail() != null) {
			Element email = document.createElement("email");
			email.setTextContent(this.getEmail());
			contatto.appendChild(email);
		}
		return contatto;
}
}
