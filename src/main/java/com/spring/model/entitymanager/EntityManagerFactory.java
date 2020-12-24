package com.spring.model.entitymanager;

import com.spring.model.aspects.MyLogger;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.File;

public class EntityManagerFactory {
    private static javax.persistence.EntityManagerFactory entityManagerFactory;

    private EntityManagerFactory() {

    }

    public void init() {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("main");
            MyLogger.log(MyLogger.Kind.INFO,this,"entityManager init");
        }catch (Exception e){
            MyLogger.log(MyLogger.Kind.FATAL,this,e.getMessage());
        }

    }


    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public void destroy() {
        entityManagerFactory.close();
    }
}
