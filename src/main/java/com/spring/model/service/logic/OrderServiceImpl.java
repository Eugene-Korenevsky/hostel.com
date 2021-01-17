package com.spring.model.service.logic;

import com.spring.model.aspects.MyLogger;
import com.spring.model.dao.OrderDao;
import com.spring.model.entity.Order;
import com.spring.model.entity.Reserve;
import com.spring.model.entity.RoomForm;
import com.spring.model.entity.ValidError;
import com.spring.model.entitymanager.EntityManagerFactory;
import com.spring.model.helpers.validator.MyValidator;
import com.spring.model.service.GenericService;
import com.spring.model.service.OrderService;
import com.spring.model.service.exceptions.EntityNotFoundException;
import com.spring.model.service.exceptions.OrderNotFountException;
import com.spring.model.service.exceptions.OrderServiceException;
import com.spring.model.service.exceptions.ValidationException;

import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OrderServiceImpl extends GenericServiceImpl<Order> implements OrderService {
    private OrderDao orderDao;

    public OrderServiceImpl() {
        super(Order.class);
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public List<Order> findByUserId(long userId) throws OrderServiceException {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Order> orders = orderDao.findOrdersByUserId(userId, entityManager);
            entityManager.getTransaction().commit();
            return orders;
        } catch (Exception e) {
            MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage());
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw new OrderServiceException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }
}
