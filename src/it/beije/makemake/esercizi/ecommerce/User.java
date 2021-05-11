package it.beije.makemake.esercizi.ecommerce;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;





@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	private String name;
	
	@Column
	private String surname;
	
	@OneToMany(cascade=CascadeType.ALL/*, fetch=FetchType.EAGER*/)  
	//	default lazy
	// cascata se modifico un id_user questo viene modificato anche nella tabella degli ordini
	@JoinColumn(name="id_user") 
	// relazione tra tabelle id della tabella User e id_user della tabella Ordini
	
	private List<Order> orders;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	
	
	public String toString() {
		StringBuilder builder = new StringBuilder()
				.append("{ id : ").append(id).append(", ")
				.append("username : ").append(username).append(", ")
				.append("name : ").append(name).append(", ")
				.append("surname : ").append(surname).append(", ")
				.append("password : ").append(password).append(" }");
		
		return builder.toString();
	}
	
}

/*
CREATE TABLE `makemake`.`user` (
`id` INT NOT NULL AUTO_INCREMENT,
`username` VARCHAR(100) NOT NULL,
`name` VARCHAR(45) NULL,
`surname` VARCHAR(45) NULL,
`password` VARCHAR(45) NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `email_UNIQUE` (`username` ASC) VISIBLE);
*/
