package com.spring.model.dao;

import com.spring.model.entity.User;

import javax.persistence.EntityManager;

public interface UserDao extends GenericDao<User> {
    public User login(String password, String email, EntityManager entityManager);

}
