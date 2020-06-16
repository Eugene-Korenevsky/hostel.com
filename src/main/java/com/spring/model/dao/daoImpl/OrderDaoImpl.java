package com.spring.model.dao.daoImpl;

import com.spring.model.dao.GenericDao;
import com.spring.model.entity.Order;

import javax.persistence.EntityManager;
import java.util.List;

public class OrderDao extends GenericDao<Order> {
    @Override
    public Order findById(long id, EntityManager entityManager) {
        return entityManager.find(Order.class,id);
    }

    @Override
    public List<Order> readAll(EntityManager entityManager) {
        return entityManager.createQuery("select i from Order i").getResultList();
    }

    @Override
    public void delete(Order entity,EntityManager entityManager) {
        entityManager.remove(entity);
    }

    @Override
    public void update(Order entity,EntityManager entityManager) {
        if (entityManager.contains(entity)) entityManager.persist(entityManager);
        else entityManager.merge(entity);
    }

    @Override
    public void create(Order entity,EntityManager entityManager) {
        entityManager.persist(entity);
    }

    @Override
    public Order createEntity(Order entity, EntityManager entityManager) {
        return entityManager.merge(entity);
    }

    @Override
    public Order updateEntity(Order entity, EntityManager entityManager) {
        return entityManager.merge(entity);
    }

}
