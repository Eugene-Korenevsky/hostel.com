package com.spring.model.service.logic;

import com.spring.model.aspects.MyLogger;
import com.spring.model.dao.RoleDao;
import com.spring.model.entity.Role;
import com.spring.model.entity.ValidError;
import com.spring.model.entitymanager.EntityManagerFactory;
import com.spring.model.helpers.validator.MyValidator;
import com.spring.model.service.RoleService;
import com.spring.model.service.exceptions.EntityNotFoundException;
import com.spring.model.service.exceptions.RoleServiceException;
import com.spring.model.service.exceptions.ValidationException;

import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> readAll() throws RoleServiceException {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Role> roles = roleDao.readAll(entityManager);
            entityManager.getTransaction().commit();
            return roles;
        } catch (Exception e) {
            MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage());
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw new RoleServiceException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Role findById(long id) throws RoleServiceException {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Role role = roleDao.findById(id, entityManager);
            if (role != null) {
                entityManager.getTransaction().commit();
                return role;
            }
            throw new EntityNotFoundException("resource not found");
        } catch (Exception e) {
            MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage());
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw new RoleServiceException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Role create(Role role) throws RoleServiceException, ValidationException {
        if (role != null) {
            Validator validator = MyValidator.getValidator();
            Set<ConstraintViolation<Role>> violations = validator.validate(role);
            if (violations.size() < 1) {
                EntityManager entityManager = EntityManagerFactory.getEntityManager();
                try {
                    entityManager.getTransaction().begin();
                    role = roleDao.createEntity(role, entityManager);
                    entityManager.getTransaction().commit();
                    return role;
                } catch (Exception e) {
                    MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage());
                    if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
                    throw new RoleServiceException(e.getMessage());
                } finally {
                    entityManager.close();
                }
            } else {
                ValidError validError = new ValidError();
                ArrayList<String> errors = new ArrayList<>();
                for (ConstraintViolation<Role> violation : violations) {
                    errors.add(violation.getMessage());
                }
                validError.setErrors(errors);
                throw new ValidationException("valid error", validError);
            }
        } else throw new ValidationException("can not be null");
    }

    @Override
    public Role update(Role role) throws RoleServiceException, ValidationException, EntityNotFoundException {
        if (role != null) {
            Validator validator = MyValidator.getValidator();
            Set<ConstraintViolation<Role>> violations = validator.validate(role);
            if (violations.size() < 1) {
                EntityManager entityManager = EntityManagerFactory.getEntityManager();
                try {
                    entityManager.getTransaction().begin();
                    Role old = roleDao.findById(role.getId(), entityManager);
                    if (old != null) {
                        role = roleDao.updateEntity(role, entityManager);
                        entityManager.getTransaction().commit();
                        return role;
                    } else throw new EntityNotFoundException("resource not found");
                } catch (IllegalArgumentException | TransactionRequiredException e) {
                    MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage());
                    if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
                    throw new RoleServiceException(e.getMessage());
                } finally {
                    entityManager.close();
                }
            } else {
                ValidError validError = new ValidError();
                ArrayList<String> errors = new ArrayList<>();
                for (ConstraintViolation<Role> violation : violations) {
                    errors.add(violation.getMessage());
                }
                validError.setErrors(errors);
                throw new ValidationException("valid error", validError);
            }
        } else throw new ValidationException("can not be null");
    }

    @Override
    public void delete(long roleId) throws RoleServiceException, EntityNotFoundException {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Role role = roleDao.findById(roleId, entityManager);
            if (role != null) {
                roleDao.delete(role, entityManager);
                entityManager.getTransaction().commit();
            } else throw new EntityNotFoundException("resource not found");
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage());
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw new RoleServiceException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }
}

