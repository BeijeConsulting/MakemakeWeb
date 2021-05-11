package it.beije.makemake.web.myEcomm.entity;

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
	private Double price;
	
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
	
	public String toString() {
		StringBuilder builder = new StringBuilder()
				.append("[Id :").append(id).append(", ")
				.append("OrderId : ").append(orderId).append(", ")
				.append("ProductId : ").append(productId).append(", ")
				.append("Price : ").append(price).append(", ")
				.append("Quantity : ").append(quantity).append(" ]");

		
		return builder.toString();
	}
}
