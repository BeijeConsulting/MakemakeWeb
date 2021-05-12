package it.beije.makemake.web.ecommerce;

import java.math.BigDecimal;
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
public class Order_item {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "id_order")
	private Integer orderId;
	
	@Column(name = "id_product")
	private Integer productId;
	
	@Column
	private BigDecimal price;
	
	@Column
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

	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	
	@Override
	public String toString() {
		return "Order_item [id=" + id + ", orderId=" + orderId + ", productId=" + productId + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal bigDecimal) {
		this.price = bigDecimal;
	}

	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	


}
//CREATE TABLE `makemake`.`order_item` (
//		  `id` INT NOT NULL AUTO_INCREMENT,
//		  `id_order` INT NOT NULL,
//		  `id_product` INT NOT NULL,
//		  `price` DECIMAL NOT NULL,
//		  `quantity` INT NOT NULL,
//		  PRIMARY KEY (`id`));