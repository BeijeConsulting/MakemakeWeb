package it.beije.makemake.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class JPAManager {

    private JPAManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory("MakemakeWeb");
    }

    private static JPAManager instance;
    private EntityManagerFactory entityManagerFactory;
    private List<EntityManager> entityManagerList = new ArrayList<>();

    public static JPAManager getJPAManager() {
        if (instance == null) {
            instance = new JPAManager();
        }

        return instance;
    }

    public EntityManager getEntityManager() {
        EntityManager entityManager;
        for (EntityManager e : entityManagerList) {
            if (!e.isOpen()) {
                e.close();
                entityManagerList.remove(e);
            }
        }
        if (entityManagerList.size() == 150) {
            throw new EntityNotFoundException("Max number of concurrent sessions reached");
        } else {
            entityManager = entityManagerFactory.createEntityManager();
            entityManagerList.add(entityManager);
        }
        return entityManager;
    }

    public void closeEntityManager(EntityManager entityManager) {
        entityManager.close();
        entityManagerList.remove(entityManager);
    }

}