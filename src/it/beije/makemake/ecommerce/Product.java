package it.beije.makemake.ecommerce;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	private String brand;
	
	@Column(name = "description")
	private String desc;
	
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
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
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
	public String toString() {
		StringBuilder builder = new StringBuilder()
				.append("{ id : ").append(id).append(", ")
				.append("name : ").append(name).append(", ")
				.append("brand : ").append(brand).append(", ")
				.append("desc : ").append(desc).append(", ")
				.append("price : ").append(price).append(" }");
		
		return builder.toString();
	}
	
}


/*
CREATE TABLE `makemake`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `brand` VARCHAR(45) NULL,
  `desc` VARCHAR(255) NULL,
  `price` DECIMAL NOT NULL DEFAULT 0,
  `image` VARCHAR(255) NULL,
  `quantity` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`));
 */