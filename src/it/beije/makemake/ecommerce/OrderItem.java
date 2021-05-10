package it.beije.makemake.ecommerce;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OrderItem")
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "id_order")
	private Integer orderId;
	
	@Column(name = "id_product")
	private Integer pruductId;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "quantity")
	private Integer quantity;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	
	public Integer getPruductId() {
		return pruductId;
	}

	public void setPruductId(Integer pruductId) {
		this.pruductId = pruductId;
	}

	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", orderId=" + orderId + ", pruductId=" + pruductId + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}
	
}
/*
CREATE TABLE `makemake`.`order_item` (
`id` INT NOT NULL AUTO_INCREMENT,
`id_order` INT NOT NULL,
`id_product` INT NOT NULL,
`price` DECIMAL NOT NULL,
`quantity` INT NOT NULL,
PRIMARY KEY (`id`));
*/
