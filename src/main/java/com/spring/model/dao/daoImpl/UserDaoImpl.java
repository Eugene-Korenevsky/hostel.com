package com.spring.model.dao.daoImpl;

import com.spring.model.dao.GenericDao;
import com.spring.model.dao.UserDao;
import com.spring.model.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {
    UserDaoImpl(){super(User.class);}

    @Override
    public User login(String password, String email,EntityManager entityManager) {
        TypedQuery<User> query = entityManager.createQuery(
                "select i from User i where i.password = ?1 and i.email = ?2", User.class
        );
        query.setParameter(1, password);
        query.setParameter(2, email);
        return query.getSingleResult();
    }

}
