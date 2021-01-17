package com.spring.model.service.logic;

import com.spring.model.aspects.MyLogger;
import com.spring.model.dao.OrderDao;
import com.spring.model.dao.ReserveDao;
import com.spring.model.entity.Order;
import com.spring.model.entity.Reserve;
import com.spring.model.entity.Room;
import com.spring.model.entitymanager.EntityManagerFactory;
import com.spring.model.service.ReserveService;
import com.spring.model.service.exceptions.EntityNotFoundException;
import com.spring.model.service.exceptions.ReserveServiceException;
import com.spring.model.service.exceptions.ValidationException;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.List;

public class ReserveServiceImpl extends GenericServiceImpl<Reserve> implements ReserveService {
    private ReserveDao reserveDao;
    private OrderDao orderDao;

    public ReserveServiceImpl() {
        super(Reserve.class);
    }

    public void setReserveDao(ReserveDao reserveDao) {
        this.reserveDao = reserveDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }


    @Override
    public List<Reserve> readAllByUserId(long userId) throws ReserveServiceException {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Reserve> reserves = reserveDao.findAllByUserId(userId, entityManager);
            entityManager.getTransaction().commit();
            return reserves;
        } catch (Exception e) {
            MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage());
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw new ReserveServiceException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Reserve create(long orderId) throws ReserveServiceException, EntityNotFoundException {
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
                reserve = reserveDao.createEntity(reserve, entityManager);
                orderDao.delete(order, entityManager);
                entityManager.getTransaction().commit();
                return reserve;
            } else throw new EntityNotFoundException("order not found");
        } catch (IllegalArgumentException e) {
            MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage());
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw new ReserveServiceException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }


    @Override
    public List<Reserve> findByDatesIntervalAndRoom(Timestamp dateIn, Timestamp dateOut, Room room)
            throws ReserveServiceException, ValidationException {
        if (dateIn != null && dateOut != null) {
            EntityManager entityManager = EntityManagerFactory.getEntityManager();
            try {
                entityManager.getTransaction().begin();
                List<Reserve> reserves = reserveDao.findByDatesIntervalAndRoom(dateIn, dateOut, room, entityManager);
                entityManager.getTransaction().commit();
                return reserves;
            } catch (Exception e) {
                MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage());
                if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
                throw new ReserveServiceException(e.getMessage());
            } finally {
                entityManager.close();
            }
        } else throw new ValidationException("Timestamps mustn't be null");
    }
}
