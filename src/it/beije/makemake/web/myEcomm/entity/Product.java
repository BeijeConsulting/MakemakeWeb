package it.beije.makemake.web.myEcomm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	private String brand;
	
	@Column
	private String desc;
	
	@Column
	private String image;
	
	@Column
	private Integer quantity;
	
	@Column
	private Double price;

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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
				.append("Name : ").append(name).append(", ")
				.append("Brand : ").append(brand).append(", ")
				.append("Description : ").append(desc).append(", ")
				.append("Price : ").append(price).append(", ")
				.append("Quantity avaible : ").append(quantity).append(" ]");

		
		return builder.toString();
	}
}
