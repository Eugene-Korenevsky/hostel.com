package com.spring.model.dao;


import com.spring.model.entity.Order;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class GenericDao<T> {

    public abstract T findById(long id,EntityManager entityManager);

    public abstract List<T> readAll(EntityManager entityManager);

    public abstract void delete(T entity,EntityManager entityManager);

    public abstract void update(T entity,EntityManager entityManager);

    public abstract void create(T entity,EntityManager entityManager);

    public abstract T createEntity(T entity,EntityManager entityManager);

    public abstract T updateEntity(T entity,EntityManager entityManager);

}
