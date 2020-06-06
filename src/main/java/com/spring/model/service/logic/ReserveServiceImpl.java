package com.spring.model.service.logic;

import com.spring.model.dao.daoImpl.OrderDao;
import com.spring.model.entity.Order;
import com.spring.model.entity.Reserve;
import com.spring.model.entity.Room;
import com.spring.model.entity.User;
import com.spring.model.entitymanager.EntityManagerFactory;
import com.spring.model.service.BaseService;
import com.spring.model.service.ReserveService;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class ReserveServiceImpl extends BaseService implements ReserveService {
    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Reserve findById(long id) {
        Reserve reserve = new Reserve();
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            reserve = (Reserve) getGenericDao().findById(id, entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }finally {
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
            reserves = getGenericDao().readAll(entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }finally {
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
            TypedQuery<Reserve> query = entityManager.createQuery(
                    "select i from Reserve i where USER_ID = ?1 ", Reserve.class
            );
            query.setParameter(1, userId);
            reserves = query.getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }finally {
            entityManager.close();
        }
        return reserves;
    }

    @Override
    public void create(long orderId) {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Order order = orderDao.findById(orderId,entityManager);
            if (order != null){
                Reserve reserve = new Reserve();
                reserve.setDateIn(order.getDateIn());
                reserve.setDateOut(order.getDateOut());
                reserve.setRoom(order.getRoom());
                reserve.setUser(order.getUser());
                getGenericDao().create(reserve, entityManager);
                orderDao.delete(order,entityManager);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }finally {
            entityManager.close();
        }
}

    @Override
    public void update(Reserve reserve) {
        if (reserve != null) {
            EntityManager entityManager = EntityManagerFactory.getEntityManager();
            try {
                entityManager.getTransaction().begin();
                getGenericDao().update(reserve, entityManager);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
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
                Reserve reserve = (Reserve) getGenericDao().findById(id,entityManager);
                if (reserve != null){
                    getGenericDao().delete(reserve, entityManager);
                }
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
            }finally {
                entityManager.close();
            }
    }
}
