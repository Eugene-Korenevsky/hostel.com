package com.spring.model.dao.daoImpl;

import com.spring.model.dao.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public  class GenericDaoImpl<T> implements GenericDao<T> {
    private Class<T> entityClass;

    public GenericDaoImpl(Class entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T findById(long id, EntityManager entityManager) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public List<T> readAll(EntityManager entityManager) {
        CriteriaQuery<T> c =
                entityManager.getCriteriaBuilder().createQuery(entityClass);
        c.select(c.from(entityClass));
        return entityManager.createQuery(c).getResultList();
    }

    @Override public  void delete(T entity,EntityManager entityManager){
        entityManager.remove(entity);
    }

    @Override
    public void update(T entity, EntityManager entityManager) {
        if (entityManager.contains(entity)) entityManager.persist(entity);
        else entityManager.merge(entity);
    }

    @Override
    public void create(T entity, EntityManager entityManager) {
      entityManager.persist(entity);
    }

    @Override
    public T createEntity(T entity, EntityManager entityManager) {
        return entityManager.merge(entity);
    }

    @Override
    public T updateEntity(T entity, EntityManager entityManager) {
        return entityManager.merge(entity);
    }

}
