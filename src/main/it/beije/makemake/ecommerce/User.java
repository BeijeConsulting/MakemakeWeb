package it.beije.makemake.ecommerce;

import javax.persistence.*;
import java.util.List;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String username;

    @Column
    private String password;

    private String name;

    private String surname;

    @OneToMany
    @JoinColumn(name="id_user")
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}' + "\n";
    }

    public String toShortString() {
        return "Name: " + name + "\n"
                + "Surname: " + surname + "\n";
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
