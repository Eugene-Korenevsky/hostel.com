package com.spring.model.service.logic;

import com.spring.model.aspects.MyLogger;
import com.spring.model.dao.OrderDao;
import com.spring.model.dao.ReserveDao;
import com.spring.model.entity.Order;
import com.spring.model.entity.Reserve;
import com.spring.model.entitymanager.EntityManagerFactory;
import com.spring.model.service.ReserveService;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class ReserveServiceImpl  implements ReserveService {
    private ReserveDao reserveDao;
    private OrderDao orderDao;

    public void setReserveDao(ReserveDao reserveDao) {
        this.reserveDao = reserveDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Reserve findById(long id) {
        Reserve reserve = new Reserve();
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            reserve = reserveDao.findById(id, entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            MyLogger.log(MyLogger.Kind.WARNING,this,e.getMessage());
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return reserve;
    }

    @Override
    public List<Reserve> readAll() {
        List<Reserve> reserves = new ArrayList<>();
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            reserves = reserveDao.readAll(entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            MyLogger.log(MyLogger.Kind.WARNING,this,e.getMessage());
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return reserves;
    }

    @Override
    public List<Reserve> readAllByUserId(long userId) {
        List<Reserve> reserves = new ArrayList<>();
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            reserves = reserveDao.findAllByUserId(userId, entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            MyLogger.log(MyLogger.Kind.WARNING,this,e.getMessage());
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return reserves;
    }

    @Override
    public void create(long orderId) {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Order order = orderDao.findById(orderId, entityManager);
            if (order != null) {
                Reserve reserve = new Reserve();
                reserve.setDateIn(order.getDateIn());
                reserve.setDateOut(order.getDateOut());
                reserve.setRoom(order.getRoom());
                reserve.setUser(order.getUser());
                reserve.setTotalPrice(order.getTotalPrice());
                reserveDao.create(reserve, entityManager);
                orderDao.delete(order, entityManager);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            MyLogger.log(MyLogger.Kind.WARNING,this,e.getMessage());
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void update(Reserve reserve) {
        if (reserve != null) {
            EntityManager entityManager = EntityManagerFactory.getEntityManager();
            try {
                entityManager.getTransaction().begin();
                reserveDao.update(reserve, entityManager);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                MyLogger.log(MyLogger.Kind.WARNING,this,e.getMessage());
                entityManager.getTransaction().rollback();
            } finally {
                entityManager.close();
            }
        }
    }

    @Override
    public void delete(long id) {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Reserve reserve = reserveDao.findById(id, entityManager);
            if (reserve != null) {
                reserveDao.delete(reserve, entityManager);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            MyLogger.log(MyLogger.Kind.WARNING,this,e.getMessage());
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
}
