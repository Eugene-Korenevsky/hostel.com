package com.spring.model.dao.daoImpl;

import com.spring.model.dao.GenericDao;
import com.spring.model.entity.Description;

import javax.persistence.EntityManager;
import java.util.List;

public class DescriptionDao extends GenericDao<Description> {
    @Override
    public Description findById(long id, EntityManager entityManager) {
        return entityManager.find(Description.class,id);
    }

    @Override
    public List<Description> readAll(EntityManager entityManager) {
        return entityManager.createQuery("select i from Description i").getResultList();
    }

    @Override
    public void delete(Description entity,EntityManager entityManager) {
        entityManager.remove(entity);
    }

    @Override
    public void update(Description entity,EntityManager entityManager) {
        if (entityManager.contains(entity)) entityManager.persist(entity);
        else entityManager.merge(entity);
    }

    @Override
    public void create(Description entity,EntityManager entityManager) {
        entityManager.persist(entity);
    }

    @Override
    public Description createEntity(Description entity, EntityManager entityManager) {
        return entityManager.merge(entity);
    }

    @Override
    public Description updateEntity(Description entity, EntityManager entityManager) {
        return entityManager.merge(entity);
    }
}
