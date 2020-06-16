package com.spring.model.entitymanager;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.File;

public class EntityManagerFactory {
    private EntityManagerFactory(){

    }
    private static final javax.persistence.EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("main");

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public void destroy(){
         entityManagerFactory.close();
    }
}
