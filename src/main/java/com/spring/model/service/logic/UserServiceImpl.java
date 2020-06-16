package com.spring.model.service.logic;

import com.spring.model.aspects.MyLogger;
import com.spring.model.dao.RoleDao;
import com.spring.model.dao.UserDao;
import com.spring.model.dao.daoImpl.RoleDaoImpl;
import com.spring.model.entity.Role;
import com.spring.model.entity.User;
import com.spring.model.entitymanager.EntityManagerFactory;
import com.spring.model.service.BaseService;
import com.spring.model.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl extends BaseService implements UserService {
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
//            user = (User) getGenericDao().findById(id, entityManager);
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
//            users = getGenericDao().readAll(entityManager);
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
                //              getGenericDao().create(user, entityManager);
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
//                getGenericDao().update(user, entityManager);
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
            //getGenericDao().delete(user, entityManager);
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
                /*TypedQuery<User> query = entityManager.createQuery(
                        "select i from User i where i.password = ?1 and i.email = ?2", User.class
                );
                query.setParameter(1, password);
                query.setParameter(2, email);
                user = query.getSingleResult();*/
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
    public User register(String email, String password, String name, String surname) {
        User user = new User();
        List<User> users;
        boolean found = false;
        if (email != null && password != null) {
            EntityManager entityManager = EntityManagerFactory.getEntityManager();
            try {
                entityManager.getTransaction().begin();
                users = getGenericDao().readAll(entityManager);
                for (User user2 : users) {
                    if (user2.getEmail().equals(email)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    long i = 1;
                    Role role = roleDao.findById(i, entityManager);
                    user.setRole(role);
                    if (name == null) user.setName("unknown");
                    else user.setName(name);
                    if (surname == null) user.setSurname("unknown");
                    else user.setSurname(surname);
                    user.setPassword(password);
                    user.setEmail(email);
                    user = userDao.createEntity(user, entityManager);
                 /*   userDao.create(user,entityManager);
//                    getGenericDao().create(user, entityManager);
                    TypedQuery<User> query = entityManager.createQuery(
                            "select i from User i where i.password = ?1 and i.email = ?2", User.class
                    );
                    query.setParameter(1, password);
                    query.setParameter(2, email);
                    user = query.getSingleResult();*/
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
//            User user = (User) getGenericDao().findById(userId, entityManager);
            user.setRole(role);
            userDao.update(user, entityManager);
//            getGenericDao().update(user, entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
}
