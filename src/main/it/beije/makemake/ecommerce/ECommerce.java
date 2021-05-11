package it.beije.makemake.ecommerce;

import it.beije.makemake.database.JPAManager;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class ECommerce {

    public static List<User> getUserList() {
        JPAManager jpaManager = JPAManager.getJPAManager();
        EntityManager entityManager = jpaManager.getEntityManager();
        Query query = entityManager.createQuery("SELECT u from User as u");
        List<User> users = query.getResultList();
        jpaManager.closeEntityManager(entityManager);
        return users;
    }

    public static void printUserList() {
        System.out.println(getUserList());
    }

    public static List<Product> getProductList() {
        JPAManager jpaManager = JPAManager.getJPAManager();
        EntityManager entityManager = jpaManager.getEntityManager();
        Query query = entityManager.createQuery("SELECT p from Product as p");
        List<Product> products = query.getResultList();
        jpaManager.closeEntityManager(entityManager);
        return products;
    }

    public static void printProductList() {
        System.out.println(getProductList());
    }


    public static void createNewOrder(int idUser, HashMap<Integer, Integer> products) {
        JPAManager jpaManager = JPAManager.getJPAManager();
        EntityManager entityManager = jpaManager.getEntityManager();
        User user = entityManager.find(User.class, idUser);
        if (user == null) {
            throw new IllegalArgumentException("Id utente non valido");
        }
        BigDecimal total = new BigDecimal(0);
        LocalDateTime dateTime = LocalDateTime.now();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Order order = new Order();
        order.setIdUser(idUser);
        order.setDate(dateTime);
        order.setStatus("ok");
        order.setTotal(total);
        entityManager.persist(order);
        Integer orderId = order.getId();
        for (Integer productId :
                products.keySet()) {
            Product product = entityManager.find(Product.class, productId);
            if (product == null) {
                throw new IllegalArgumentException("Id prodotto "+ productId + " non valido");
            }
            OrderItem orderItem = new OrderItem();
            orderItem.setIdOrder(orderId);
            orderItem.setIdProduct(productId);
            orderItem.setQuantity(products.get(productId));
            orderItem.setPrice(product.getPrice());
            entityManager.persist(orderItem);
            total = total.add(product.getPrice().multiply(BigDecimal.valueOf(products.get(productId))));
        }
        order.setTotal(total);
        entityManager.merge(order);
        entityTransaction.commit();
        jpaManager.closeEntityManager(entityManager);
    }

    public static String getOrderDetails(Integer orderId) {
        StringBuilder output = new StringBuilder();
        JPAManager jpaManager = JPAManager.getJPAManager();
        EntityManager entityManager = jpaManager.getEntityManager();
        Order order = getOrder(orderId);
        //append order info
        output.append("ORDER INFO: " + "\n");
        output.append(order.toShortString());
        //get data about user
        output.append("USER INFO: " + "\n");
        User user = getUser(order.getIdUser());
        output.append(user.toShortString());
        //get data about products
        List<OrderItem> orderItems = order.getOrderItems();
        output.append(getOrderItemsInfo(orderItems));
        jpaManager.closeEntityManager(entityManager);
        return output.toString();
    }

    private static String getOrderItemsInfo(List<OrderItem> orderItems) {
        StringBuilder output = new StringBuilder();
        for (OrderItem orderItem : orderItems) {
            Integer productId = orderItem.getIdProduct();
            Product product = getProduct(productId);
            output.append(product.toShortString());
            output.append("Price (at the time of purchase) : " + orderItem.getPrice());
            output.append("Ordered amount: " + orderItem.getQuantity() + "\n");
            output.append("------------------------------\n");
        }
        return output.toString();
    }

    private static User getUser(Integer userId) {
        JPAManager jpaManager = JPAManager.getJPAManager();
        EntityManager entityManager = jpaManager.getEntityManager();
        User user = entityManager.find(User.class, userId);
        jpaManager.closeEntityManager(entityManager);
        return user;
    }

    public static Product getProduct(Integer productId) {
        JPAManager jpaManager = JPAManager.getJPAManager();
        EntityManager entityManager = jpaManager.getEntityManager();
        Product product = entityManager.find(Product.class, productId);
        jpaManager.closeEntityManager(entityManager);
        return product;
    }

    public static Order getOrder(Integer orderId) {
        JPAManager jpaManager = JPAManager.getJPAManager();
        EntityManager entityManager = jpaManager.getEntityManager();
        Order order = entityManager.find(Order.class, orderId);
        jpaManager.closeEntityManager(entityManager);
        return order;
    }




}