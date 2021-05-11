package it.beije.makemake.web.ecommerce;

import java.time.LocalDateTime;
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
@Table(name = "`order`")
public class Order { 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private LocalDateTime date;
	
	@Column(name = "id_user")
	private Integer userId;
	
	@Column
	private String status;
	
	@Column
	private Double total;

	@OneToMany(cascade=CascadeType.ALL/*, fetch=FetchType.EAGER*/)
	@JoinColumn(name="id_order")
	private static List<Order_item> orderId;

	public static List<Order_item> getOrderId() {
		return orderId;
	}	
	public void setOrderId(List<Order_item> orderId) {
		this.orderId = orderId;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	
	public  Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", date=" + date + ", userId=" + userId + ", status=" + status + ", total=" + total
				+ "]";
	}

	

}
//CREATE TABLE `makemake`.`order` (
//		  `id` INT NOT NULL AUTO_INCREMENT,
//		  `date` DATETIME NOT NULL,
//		  `id_user` INT NOT NULL,
//		  `status` VARCHAR(45) NOT NULL,
//		  `total` DECIMAL NOT NULL,
//		  PRIMARY KEY (`id`));



