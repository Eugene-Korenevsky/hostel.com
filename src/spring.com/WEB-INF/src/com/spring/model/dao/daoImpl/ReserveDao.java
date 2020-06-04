package com.spring.model.dao.daoImpl;

import com.spring.model.dao.GenericDao;
import com.spring.model.entity.Reserve;

import javax.persistence.EntityManager;
import java.util.List;

public class ReserveDao extends GenericDao<Reserve> {

    @Override
    public Reserve findById(long id, EntityManager entityManager) {
        return entityManager.find(Reserve.class,id);
    }

    @Override
    public List<Reserve> readAll(EntityManager entityManager) {
        return entityManager.createQuery("select i from Reserve i").getResultList();
    }

    @Override
    public void delete(Reserve entity,EntityManager entityManager) {
        entityManager.remove(entity);
    }

    @Override
    public void update(Reserve entity,EntityManager entityManager) {
        if (entityManager.contains(entity))entityManager.persist(entity);
        else entityManager.merge(entity);
    }

    @Override
    public void create(Reserve entity,EntityManager entityManager) {
        entityManager.persist(entity);
    }

    @Override
    public Reserve createEntity(Reserve entity, EntityManager entityManager) {
        return entityManager.merge(entity);
    }

    @Override
    public Reserve updateEntity(Reserve entity, EntityManager entityManager) {
        return entityManager.merge(entity);
    }
}
