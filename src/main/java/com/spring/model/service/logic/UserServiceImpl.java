package com.spring.model.service.logic;

import com.spring.model.aspects.MyLogger;
import com.spring.model.dao.RoleDao;
import com.spring.model.dao.UserDao;
import com.spring.model.entity.Role;
import com.spring.model.entity.User;
import com.spring.model.entitymanager.EntityManagerFactory;
import com.spring.model.service.UserService;
import com.spring.model.service.exceptions.EmailIsExistException;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl  implements UserService {
    private UserDao userDao;
    private RoleDao roleDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public User findById(long id) {
        User user = new User();
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            user = userDao.findById(id, entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return user;
    }

    @Override
    public List<User> readAll() {
        List<User> users = new ArrayList<>();
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            users = userDao.readAll(entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return users;
    }

    @Override
    public void create(User user) {
        if (user != null) {
            EntityManager entityManager = EntityManagerFactory.getEntityManager();
            try {
                entityManager.getTransaction().begin();
                userDao.create(user, entityManager);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
            } finally {
                entityManager.close();
            }
        }
    }

    @Override
    public void update(User user) {
        if (user != null) {
            EntityManager entityManager = EntityManagerFactory.getEntityManager();
            try {
                entityManager.getTransaction().begin();
                userDao.update(user, entityManager);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
            } finally {
                entityManager.close();
            }
        }
    }

    @Override
    public void delete(long userId) {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            User user = userDao.findById(userId, entityManager);
            userDao.delete(user, entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }


    @Override
    public User login(String email, String password) {
        if (email != null && password != null) {
            EntityManager entityManager = EntityManagerFactory.getEntityManager();
            User user = new User();
            try {
                entityManager.getTransaction().begin();
                user = userDao.login(password, email, entityManager);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
            } finally {
                entityManager.close();
            }
            return user;
        } else {
            return new User();
        }
    }

    @Override
    public User register(User user) throws EmailIsExistException {
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
                        System.out.println("found");
                        break;
                    }
                }
                if (!found) {
                    long i = 1;
                    Role role = roleDao.findById(i, entityManager);
                    user.setRole(role);
                    userDao.createEntity(user, entityManager);
                } else{
                    throw new EmailIsExistException();
                }
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                MyLogger.log(e, this, "ERROR");
            } finally {
                entityManager.close();
            }
            return user;
        } else return new User();
    }

    @Override
    public void changeUserRole(long userId, long roleId) {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Role role = roleDao.findById(roleId, entityManager);
            User user = userDao.findById(userId, entityManager);
            user.setRole(role);
            userDao.update(user, entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
}
