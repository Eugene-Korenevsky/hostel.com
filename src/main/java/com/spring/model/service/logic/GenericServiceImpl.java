package com.spring.model.service.logic;

import com.spring.model.aspects.MyLogger;
import com.spring.model.dao.GenericDao;
import com.spring.model.dao.daoImpl.GenericDaoImpl;
import com.spring.model.entity.Order;
import com.spring.model.entity.Reserve;
import com.spring.model.entity.ValidError;
import com.spring.model.entitymanager.EntityManagerFactory;
import com.spring.model.helpers.validator.MyValidator;
import com.spring.model.service.GenericService;
import com.spring.model.service.exceptions.*;

import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GenericServiceImpl<T> implements GenericService<T> {
    private Class<T> entityClass;
    private GenericDaoImpl<T> genericDao;


    protected GenericServiceImpl(Class entityClass) {
        this.entityClass = entityClass;
        genericDao = new GenericDaoImpl(entityClass);
    }
    @Override
    public T findById(long id) throws ServiceException, EntityNotFoundException {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            T entity =  genericDao.findById(id, entityManager);
            if (entity != null) {
                entityManager.getTransaction().commit();
                return  entity;
            } else throw new EntityNotFoundException("resource not found");
        } catch (IllegalArgumentException e) {
            MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage());
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw new ServiceException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<T> readAll() throws ServiceException {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<T> entities = genericDao.readAll(entityManager);
            entityManager.getTransaction().commit();
            return entities;
        } catch (Exception e) {
            MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage());
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw new ServiceException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(long id) throws EntityNotFoundException, ServiceException {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            T entity = genericDao.findById(id, entityManager);
            if (entity != null) {
                genericDao.delete(entity, entityManager);
                entityManager.getTransaction().commit();
            } else throw new OrderNotFountException("order not found");
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage());
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw new ServiceException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public T create(T entity) throws ServiceException, ValidationException {
        if (entity != null) {
            Validator validator = MyValidator.getValidator();
            Set<ConstraintViolation<T>> violations = validator.validate(entity);
            if (violations.size() < 1) {
                EntityManager entityManager = EntityManagerFactory.getEntityManager();
                try {
                    entityManager.getTransaction().begin();
                    entity = genericDao.createEntity(entity, entityManager);
                    entityManager.getTransaction().commit();
                    return entity;
                } catch (Exception e) {
                    MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage());
                    if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
                    throw new ServiceException(e.getMessage());
                } finally {
                    entityManager.close();
                }
            } else {
                ValidError validError = new ValidError();
                ArrayList<String> errors = new ArrayList<>();
                for (ConstraintViolation<T> violation : violations) {
                    errors.add(violation.getMessage());
                }
                validError.setErrors(errors);
                throw new ValidationException("valid error", validError);
            }
        } else throw new ValidationException("can not be null");
    }

    @Override
    public T update(T entity,long id) throws ServiceException, OrderNotFountException, ValidationException {
        if (entity != null) {
            Validator validator = MyValidator.getValidator();
            Set<ConstraintViolation<T>> violations = validator.validate(entity);
            if (violations.size() < 1) {
                EntityManager entityManager = EntityManagerFactory.getEntityManager();
                try {
                    entityManager.getTransaction().begin();
                    T old = genericDao.findById(id, entityManager);
                    if (old != null) {
                        entity = genericDao.updateEntity(entity, entityManager);
                        entityManager.getTransaction().commit();
                        return entity;
                    } else throw new OrderNotFountException("order not found");
                } catch (IllegalArgumentException | TransactionRequiredException e) {
                    MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage());
                    if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
                    throw new ServiceException(e.getMessage());
                } finally {
                    entityManager.close();
                }
            } else {
                ValidError validError = new ValidError();
                ArrayList<String> errors = new ArrayList<>();
                for (ConstraintViolation<T> violation : violations) {
                    errors.add(violation.getMessage());
                }
                validError.setErrors(errors);
                throw new ValidationException("valid error", validError);
            }
        } else throw new ValidationException("can not be null");
    }
}
