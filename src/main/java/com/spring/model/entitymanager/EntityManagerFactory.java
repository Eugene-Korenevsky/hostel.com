package com.spring.model.entitymanager;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.File;

public class EntityManagerFactory {
    private static javax.persistence.EntityManagerFactory entityManagerFactory;

    private EntityManagerFactory() {

    }

    public void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("main");
    }


    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public void destroy() {
        entityManagerFactory.close();
    }
}
