package com.spring.model.service.logic;

import com.spring.model.dao.OrderDao;
import com.spring.model.entity.Order;
import com.spring.model.entity.Room;
import com.spring.model.entity.User;
import com.spring.model.entitymanager.EntityManagerFactory;
import com.spring.model.service.BaseService;
import com.spring.model.service.OrderService;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl extends BaseService implements OrderService {
    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Order findById(long id) {
        Order order = new Order();
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            orderDao.findById(id,entityManager);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }finally {
            entityManager.close();
        }
        return order;
    }

    @Override
    public List<Order> readAll() {
        List<Order> orders = new ArrayList<>();
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            orderDao.readAll(entityManager);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }finally {
            entityManager.close();
        }
        return orders;
    }

    @Override
    public List<Order> findByUserId(long userId) {
        List<Order> orders = new ArrayList<>();
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            orders = orderDao.findOrdersByUserId(userId,entityManager);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }finally {
            entityManager.close();
        }
        return orders;
    }

    @Override
    public void create(Order order) {
       if (order != null){
           EntityManager entityManager = EntityManagerFactory.getEntityManager();
           try {
               entityManager.getTransaction().begin();
               orderDao.create(order,entityManager);
               entityManager.getTransaction().commit();
           }catch (Exception e){
               entityManager.getTransaction().rollback();
           }finally {
               entityManager.close();
           }
       }
    }

    @Override
    public void delete(long id) {
           EntityManager entityManager = EntityManagerFactory.getEntityManager();
           try {
               entityManager.getTransaction().begin();
               Order order = orderDao.findById(id,entityManager);
               if (order != null) {
                   orderDao.delete(order,entityManager);
               }
               entityManager.getTransaction().commit();
           }catch (Exception e){
               entityManager.getTransaction().rollback();
           }finally {
               entityManager.close();
           }
    }

    @Override
    public void update(Order order) {
      if (order != null){
          EntityManager entityManager = EntityManagerFactory.getEntityManager();
          try {
              entityManager.getTransaction().begin();
              orderDao.update(order,entityManager);
              entityManager.getTransaction().commit();
          }catch (Exception e){
              entityManager.getTransaction().rollback();
          }finally {
              entityManager.close();
          }
      }
    }
}
