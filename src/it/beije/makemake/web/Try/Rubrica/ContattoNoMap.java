package it.beije.makemake.web.Try.Rubrica;



public class ContattoNoMap {
	

		private String nome;
		private String cognome;
		private String telefono;
		private String email;
		
		
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
					.append("[Nome : ").append(nome).append(", ")
					.append("Cognome : ").append(cognome).append(", ")
					.append("Telefono : ").append(telefono).append(", ")
					.append("Email : ").append(email).append("] ");
			
			return builder.toString();
		}
		
		public boolean equals(ContattoNoMap c1, ContattoNoMap c2) {
			if(c1.nome==c2.nome)
				if(c1.cognome==c2.cognome)
						if(c1.telefono==c2.telefono)
						return true;
			
			return false;
		}
	}


