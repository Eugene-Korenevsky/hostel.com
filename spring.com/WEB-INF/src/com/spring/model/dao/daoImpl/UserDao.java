package com.spring.model.dao.daoImpl;

import com.spring.model.dao.GenericDao;
import com.spring.model.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

public class UserDao extends GenericDao<User> {
    @Override
    public User findById(long id, EntityManager entityManager) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> readAll(EntityManager entityManager) {
        return entityManager.createQuery("select i from User i").getResultList();
    }

    @Override
    public void delete(User entity,EntityManager entityManager) {
        entityManager.remove(entity);
    }

    @Override
    public void update(User entity,EntityManager entityManager) {
        if (entityManager.contains(entity))entityManager.persist(entity);
        else entityManager.merge(entity);
    }

    @Override
    public void create(User entity,EntityManager entityManager) {
        entityManager.persist(entity);
    }

    @Override
    public User createEntity(User entity, EntityManager entityManager) {
        return entityManager.merge(entity);
    }

    @Override
    public User updateEntity(User entity, EntityManager entityManager) {
        return entityManager.merge(entity);
    }
}
