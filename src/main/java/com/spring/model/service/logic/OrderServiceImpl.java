package com.spring.model.service.logic;

import com.spring.model.aspects.MyLogger;
import com.spring.model.dao.OrderDao;
import com.spring.model.entity.Order;
import com.spring.model.entity.RoomForm;
import com.spring.model.entity.ValidError;
import com.spring.model.entitymanager.EntityManagerFactory;
import com.spring.model.helpers.validator.MyValidator;
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

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Order findById(long id) throws OrderServiceException, OrderNotFountException {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Order order = orderDao.findById(id, entityManager);
            if (order != null) {
                entityManager.getTransaction().commit();
                return order;
            } else throw new OrderNotFountException("order not found");
        } catch (IllegalArgumentException e) {
            MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage());
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw new OrderServiceException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Order> readAll() throws OrderServiceException {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Order> orders = orderDao.readAll(entityManager);
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

    @Override
    public Order create(Order order) throws OrderServiceException, ValidationException {
        if (order != null) {
            Validator validator = MyValidator.getValidator();
            Set<ConstraintViolation<Order>> violations = validator.validate(order);
            if (violations.size() < 1) {
                EntityManager entityManager = EntityManagerFactory.getEntityManager();
                try {
                    entityManager.getTransaction().begin();
                    order = orderDao.createEntity(order, entityManager);
                    entityManager.getTransaction().commit();
                    return order;
                } catch (Exception e) {
                    MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage());
                    if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
                    throw new OrderServiceException(e.getMessage());
                } finally {
                    entityManager.close();
                }
            } else {
                ValidError validError = new ValidError();
                ArrayList<String> errors = new ArrayList<>();
                for (ConstraintViolation<Order> violation : violations) {
                    errors.add(violation.getMessage());
                }
                validError.setErrors(errors);
                throw new ValidationException("valid error", validError);
            }
        } else throw new ValidationException("can not bu null");
    }

    @Override
    public void delete(long id) throws OrderServiceException, OrderNotFountException {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Order order = orderDao.findById(id, entityManager);
            if (order != null) {
                orderDao.delete(order, entityManager);
                entityManager.getTransaction().commit();
            } else throw new OrderNotFountException("order not found");
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage());
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw new OrderServiceException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Order update(Order order) throws OrderServiceException, OrderNotFountException, ValidationException {
        if (order != null) {
            Validator validator = MyValidator.getValidator();
            Set<ConstraintViolation<Order>> violations = validator.validate(order);
            if (violations.size() < 1) {
                EntityManager entityManager = EntityManagerFactory.getEntityManager();
                try {
                    entityManager.getTransaction().begin();
                    Order old = orderDao.findById(order.getId(), entityManager);
                    if (old != null) {
                        order = orderDao.updateEntity(order, entityManager);
                        entityManager.getTransaction().commit();
                        return order;
                    } else throw new OrderNotFountException("order not found");
                } catch (IllegalArgumentException | TransactionRequiredException e) {
                    MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage());
                    if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
                    throw new OrderServiceException(e.getMessage());
                } finally {
                    entityManager.close();
                }
            } else {
                ValidError validError = new ValidError();
                ArrayList<String> errors = new ArrayList<>();
                for (ConstraintViolation<Order> violation : violations) {
                    errors.add(violation.getMessage());
                }
                validError.setErrors(errors);
                throw new ValidationException("valid error", validError);
            }
        } else throw new ValidationException("can not be null");
    }
}
