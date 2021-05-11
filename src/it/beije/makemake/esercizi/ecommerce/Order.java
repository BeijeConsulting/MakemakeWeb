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
import javax.persistence.Table;


@Entity
@Table(name = "\"order\"")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column (name = "id_user")
	private Integer userId;
	
	@Column
	private String status;
	
	@Column
	private Double total;
	
	@Column
	private String date;
	
	@OneToMany(cascade=CascadeType.ALL/*, fetch=FetchType.EAGER*/)  
	
	
	@JoinColumn(name="id_order") 
	
	
	private List<Order_Item> orderItems;
	

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_User() {
		return userId;
	}

	public void setId_User(Integer id_user) {
		this. userId =  id_user;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this. status =  status;
	}
	
	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this. total =  total;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this. date =  date;
	}
	
	public List<Order_Item> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<Order_Item> orderItems) {
		this.orderItems = orderItems;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder()
				.append("{ id : ").append(id).append(", ")
				.append("date : ").append(date).append(", ")
				.append("id_user : ").append(userId).append(", ")
				.append("status : ").append(status).append(", ")
				.append("total : ").append(total).append(" }");
		
		return builder.toString();
	}
	
}