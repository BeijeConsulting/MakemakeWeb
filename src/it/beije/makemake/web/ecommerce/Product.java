package it.beije.makemake.web.ecommerce;

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
	private String description;

	@Column
	private Double price;
	
	@Column
	private String image;
	
	@Column
	private Integer quantity;


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

	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
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
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", brand=" + brand + ", description=" + description + ", price="
				+ price + ", image=" + image + ", quantity=" + quantity + "]";
	}

}


//CREATE TABLE `makemake`.`product` (
//		  `id` INT NOT NULL AUTO_INCREMENT,
//		  `name` VARCHAR(45) NULL,
//		  `brand` VARCHAR(45) NULL,
//		  `desc` VARCHAR(255) NULL,
//		  `price` DECIMAL NOT NULL DEFAULT 0,
//		  `image` VARCHAR(255) NULL,
//		  `quantity` INT NOT NULL DEFAULT 0,
//		  PRIMARY KEY (`id`));
//		  