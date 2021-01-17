package com.spring.model.service.logic;

import com.spring.model.aspects.MyLogger;
import com.spring.model.dao.RoleDao;
import com.spring.model.dao.UserDao;
import com.spring.model.entity.Role;
import com.spring.model.entity.User;
import com.spring.model.entity.ValidError;
import com.spring.model.entitymanager.EntityManagerFactory;
import com.spring.model.helpers.validator.MyValidator;
import com.spring.model.service.UserService;
import com.spring.model.service.exceptions.EmailIsExistException;
import com.spring.model.service.exceptions.EntityNotFoundException;
import com.spring.model.service.exceptions.UserServiceException;
import com.spring.model.service.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserServiceImpl extends GenericServiceImpl<User> implements UserService {

    private String defaultRole;
    private UserDao userDao;
    private RoleDao roleDao;

    public UserServiceImpl(){
        super(User.class);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public void setDefaultRole(String defaultRole) {
        this.defaultRole = defaultRole;
    }


    @Override
    public User login(String email, String password) throws UserServiceException,ValidationException {
        if (email != null && password != null) {
            EntityManager entityManager = EntityManagerFactory.getEntityManager();
            User user = new User();
            try {
                entityManager.getTransaction().begin();
                user = userDao.login(password, email, entityManager);
                entityManager.getTransaction().commit();
                return user;
            } catch (Exception e) {
                MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage());
                if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
                throw new UserServiceException(e.getMessage());
            } finally {
                entityManager.close();
            }
        } else {
            throw new ValidationException("email and password can't be null");
        }
    }

    @Override
    public User register(User user) throws EmailIsExistException, UserServiceException {
        List<User> users;
        boolean found = false;
        if (user.getEmail() != null && user.getPassword() != null) {
            EntityManager entityManager = EntityManagerFactory.getEntityManager();
            try {
                entityManager.getTransaction().begin();
                users = userDao.readAll(entityManager);
                for (User user2 : users) {
                    if (user2.getEmail().equals(user.getEmail())) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    Role role = roleDao.findByName(defaultRole, entityManager);
                    user.setRole(role);
                    userDao.createEntity(user, entityManager);
                    entityManager.getTransaction().commit();
                    return user;
                } else {
                    throw new EmailIsExistException();
                }
            } catch (Exception e) {
                if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
                MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage() + " " + defaultRole);
                throw new UserServiceException(e.getMessage());
            } finally {
                entityManager.close();
            }
        } else throw new UserServiceException("email and password can't be null");
    }

    @Override
    public User changeUserRole(long userId, long roleId) throws UserServiceException, EntityNotFoundException {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Role role = roleDao.findById(roleId, entityManager);
            User user = userDao.findById(userId, entityManager);
            if (user != null) {
                if (role != null) {
                    user.setRole(role);
                    user = userDao.updateEntity(user, entityManager);
                    entityManager.getTransaction().commit();
                    return user;
                } else throw new EntityNotFoundException("role is not exist");
            } else throw new EntityNotFoundException("user isn't exist ");
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            MyLogger.log(MyLogger.Kind.WARNING, this, e.getMessage());
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw new UserServiceException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }
}
