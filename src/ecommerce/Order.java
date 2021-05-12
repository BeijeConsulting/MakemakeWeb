package ecommerce;


import java.time.LocalDate;
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
	
	@Column
	private LocalDate date;
	
	@Column(name = "id_user")
	private Integer userId;
	
	@Column
	private String status;
	
	@Column
	private double total;
	
	@OneToMany(cascade=CascadeType.ALL/*, fetch=FetchType.EAGER*/)
	@JoinColumn(name="id_order")
	private List<OrderItem> ordersItem;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserID(Integer userID) {
		this.userId = userID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", date=" + date + ", userId=" + userId + ", status=" + status + ", total=" + total
				+ "]";
	}

	public List<OrderItem> getOrdersItem() {
		return ordersItem;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (Double.doubleToLongBits(total) != Double.doubleToLongBits(other.total))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	
	public Order(LocalDate date, Integer userId, String status, double total) {
		
		this.date = date;
		this.userId = userId;
		this.status = status;
		this.total = total;
	}

	public Order() {
		
	}

	
	
	
}
/*
CREATE TABLE `makemake`.`order` (
`id` INT NOT NULL AUTO_INCREMENT,
`date` DATETIME NOT NULL,
`id_user` INT NOT NULL,
`status` VARCHAR(45) NOT NULL,
`total` DECIMAL NOT NULL,
PRIMARY KEY (`id`));
*/
