package it.beije.makemake.ecommerce;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="\"Order\"")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime date;

    @Column(name="id_user")
    private Integer idUser;

    private String status;

    private BigDecimal total;

    @OneToMany
    @JoinColumn(name="id_order")
    private List<OrderItem> orderItems;


    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String toShortString() {
        return
                "Order id: " + id + "\n"
                        + "Total price: " + total + "\n";
    }

}