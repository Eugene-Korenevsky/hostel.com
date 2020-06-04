package com.spring.model.service.logic;

import com.spring.model.entity.Order;
import com.spring.model.entitymanager.EntityManagerFactory;
import com.spring.model.service.BaseService;
import com.spring.model.service.OrderService;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl extends BaseService implements OrderService {
    @Override
    public Order findById(long id) {
        Order order = new Order();
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            order = (Order) getGenericDao().findById(id,entityManager);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }
        return order;
    }

    @Override
    public List<Order> readAll() {
        List<Order> orders = new ArrayList<>();
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            orders = getGenericDao().readAll(entityManager);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }
        return orders;
    }

    @Override
    public List<Order> findByUserId(long userId) {
        List<Order> orders = new ArrayList<>();
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
           // getGenericDao().getEntityManager().getTransaction().begin();
            TypedQuery<Order> query = entityManager.createQuery(
                    "select i from Order i where USER_ID = ?1",Order.class
            );
            query.setParameter(1,userId);
            orders = query.getResultList();
            entityManager.getTransaction().commit();
            //getGenericDao().getEntityManager().getTransaction().rollback();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            //getGenericDao().getEntityManager().getTransaction().rollback();
        }
        return orders;
    }

    @Override
    public void create(Order order) {
       if (order != null){
           EntityManager entityManager = EntityManagerFactory.getEntityManager();
           try {
               entityManager.getTransaction().begin();
               //getGenericDao().getEntityManager().getTransaction().begin();
               getGenericDao().create(order,entityManager);
               entityManager.getTransaction().commit();
               //getGenericDao().getEntityManager().getTransaction().commit();
           }catch (Exception e){
               entityManager.getTransaction().rollback();
               //getGenericDao().getEntityManager().getTransaction().rollback();
           }
       }
    }

    @Override
    public void delete(long id) {
           EntityManager entityManager = EntityManagerFactory.getEntityManager();
           try {
               entityManager.getTransaction().begin();
               Order order = (Order) getGenericDao().findById(id,entityManager);
               if (order != null) {
                   getGenericDao().delete(order,entityManager);
               }
               entityManager.getTransaction().commit();
           }catch (Exception e){
               entityManager.getTransaction().rollback();
           }
    }

    @Override
    public void update(Order order) {
      if (order != null){
          EntityManager entityManager = EntityManagerFactory.getEntityManager();
          try {
              entityManager.getTransaction().begin();
             // getGenericDao().getEntityManager().getTransaction().begin();
              getGenericDao().update(order,entityManager);
              //getGenericDao().getEntityManager().getTransaction().commit();
              entityManager.getTransaction().commit();
          }catch (Exception e){
              entityManager.getTransaction().rollback();
             // getGenericDao().getEntityManager().getTransaction().rollback();
          }
      }
    }
}
