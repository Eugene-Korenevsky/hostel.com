package com.spring.model.service.logic;

import com.spring.model.aspects.MyLogger;
import com.spring.model.dao.daoImpl.RoleDao;
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
    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public User findById(long id) {
        User user = new User();
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            user = (User) getGenericDao().findById(id, entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return user;
    }

    @Override
    public List<User> readAll() {
        List<User> users = new ArrayList<>();
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            users = getGenericDao().readAll(entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return users;
    }

    @Override
    public void create(User user) {
        if (user != null) {
            EntityManager entityManager = EntityManagerFactory.getEntityManager();
            try {
                entityManager.getTransaction().begin();
                getGenericDao().create(user, entityManager);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    @Override
    public void update(User user) {
        if (user != null) {
            EntityManager entityManager = EntityManagerFactory.getEntityManager();
            try {
                entityManager.getTransaction().begin();
                getGenericDao().update(user, entityManager);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    @Override
    public void delete(User user) {
        if (user != null) {
            EntityManager entityManager = EntityManagerFactory.getEntityManager();
            try {
                entityManager.getTransaction().begin();
                getGenericDao().delete(user, entityManager);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    @Override
    public User login(String email, String password) {
        if (email != null && password != null) {
            EntityManager entityManager = EntityManagerFactory.getEntityManager();
            User user = new User();
            try {
                entityManager.getTransaction().begin();
                TypedQuery<User> query = entityManager.createQuery(
                        "select i from User i where i.password = ?1 and i.email = ?2", User.class
                );
                query.setParameter(1, password);
                query.setParameter(2, email);
                user = query.getSingleResult();
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
            }
            return user;
        } else {
            return new User();
        }
    }

    @Override
    public User register(String email,String password,String name,String surname)  {
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
                    Role role = roleDao.findById(i,entityManager);
                    user.setRole(role);
                    if (name == null) user.setName("unknown");
                    else user.setName(name);
                    if (surname == null) user.setSurname("unknown");
                    else user.setSurname(surname);
                    user.setPassword(password);
                    user.setEmail(email);
                    getGenericDao().create(user, entityManager);
                    TypedQuery<User> query = entityManager.createQuery(
                            "select i from User i where i.password = ?1 and i.email = ?2", User.class
                    );
                    query.setParameter(1, password);
                    query.setParameter(2, email);
                    user = query.getSingleResult();
                }
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                MyLogger.log(e,this,"ERROR");
            }
            return user;
        } else return new User();
    }

    @Override
    public void changeUserRole(long userId, long roleId) {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Role role = roleDao.findById(roleId,entityManager);
            User user = (User) getGenericDao().findById(userId,entityManager);
            user.setRole(role);
            getGenericDao().update(user,entityManager);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }
    }
}
