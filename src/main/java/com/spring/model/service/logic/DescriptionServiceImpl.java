package com.spring.model.service.logic;

import com.spring.model.aspects.MyLogger;
import com.spring.model.dao.DescriptionDao;
import com.spring.model.entity.Description;
import com.spring.model.entity.RoomForm;
import com.spring.model.entitymanager.EntityManagerFactory;
import com.spring.model.helpers.validator.MyValidator;
import com.spring.model.service.DescriptionService;
import com.spring.model.service.exceptions.DescriptionServiceException;
import com.spring.model.service.exceptions.EntityNotFoundException;
import com.spring.model.service.exceptions.ValidationException;

import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DescriptionServiceImpl implements DescriptionService {
    private DescriptionDao descriptionDao;

    public void setDescriptionDao(DescriptionDao descriptionDao) {
        this.descriptionDao = descriptionDao;
    }

    @Override
    public Description findById(long id, boolean withRooms) throws DescriptionServiceException, EntityNotFoundException {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Description description = descriptionDao.findById(id, entityManager);
            if (description != null) {
                if (withRooms) description.getRooms().size();
                entityManager.getTransaction().commit();
                return description;
            } else {
                throw new EntityNotFoundException("resource not found");
            }
        } catch (IllegalArgumentException e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage());
            throw new DescriptionServiceException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Description> readAll(boolean withRooms) throws DescriptionServiceException {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Description> descriptions = descriptionDao.readAll(entityManager);
            if (withRooms) {
                for (Description description : descriptions) {
                    description.getRooms().size();
                }
            }
            entityManager.getTransaction().commit();
            return descriptions;
        } catch (Exception e) {
            MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage());
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw new DescriptionServiceException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(long id) throws DescriptionServiceException, EntityNotFoundException {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Description description = descriptionDao.findById(id, entityManager);
            if (description != null) {
                descriptionDao.delete(description, entityManager);
                entityManager.getTransaction().commit();
            } else throw new EntityNotFoundException("resource not found");
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage());
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw new DescriptionServiceException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Description update(Description description) throws DescriptionServiceException, EntityNotFoundException, ValidationException {
        if (description != null) {
            Validator validator = MyValidator.getValidator();
            Set<ConstraintViolation<Description>> violations = validator.validate(description);
            if (violations.size() < 1) {
                EntityManager entityManager = EntityManagerFactory.getEntityManager();
                try {
                    Description old = descriptionDao.findById(description.getId(), entityManager);
                    if (old != null) {
                        entityManager.getTransaction().begin();
                        description = descriptionDao.updateEntity(description, entityManager);
                        entityManager.getTransaction().commit();
                        return description;
                    } else throw new EntityNotFoundException("resource not found");
                } catch (IllegalArgumentException | TransactionRequiredException e) {
                    MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage());
                    if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
                    throw new DescriptionServiceException(e.getMessage());
                } finally {
                    entityManager.close();
                }
            } else {
                ArrayList<String> validationErrors = new ArrayList<>();
                for (ConstraintViolation<Description> violation : violations) {
                    validationErrors.add(violation.getMessage());
                }
                throw new ValidationException(validationErrors.toString());
            }
        } else throw new ValidationException("description can't bu null");
    }

    @Override
    public Description create(String name) throws DescriptionServiceException, ValidationException {
        if (name != null) {
            Description description = new Description();
            description.setDescription(name);
            EntityManager entityManager = EntityManagerFactory.getEntityManager();
            description.setDescription(name);
            try {
                entityManager.getTransaction().begin();
                description = descriptionDao.createEntity(description, entityManager);
                entityManager.getTransaction().commit();
                return description;
            } catch (Exception e) {
                MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage());
                if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
                throw new DescriptionServiceException(e.getMessage());
            } finally {
                entityManager.close();
            }
        } else throw new ValidationException("min name size is 1");
    }
}
