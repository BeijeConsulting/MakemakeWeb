package it.beije.makemake.addressbook;

import it.beije.makemake.database.JPAManager;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class AddressBookDatabase {

    public static List<Contact> search(String name){
        JPAManager jpaManager = JPAManager.getJPAManager();
        EntityManager entityManager = jpaManager.getEntityManager();
        Query query = entityManager.createQuery("SELECT r FROM rubrica as r" +
                "WHERE r.name = :name");
        query.setParameter("name", name);
        List<Contact> contacts = query.getResultList();
        jpaManager.closeEntityManager(entityManager);
        return contacts;

    }
}
