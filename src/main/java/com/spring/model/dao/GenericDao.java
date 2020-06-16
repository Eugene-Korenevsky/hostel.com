package com.spring.model.dao;

import javax.persistence.EntityManager;
import java.util.List;

public interface GenericDao<T> {

    public  T findById(long id,EntityManager entityManager);

    public  List<T> readAll(EntityManager entityManager);

    public  void delete(T entity,EntityManager entityManager);

    public  void update(T entity,EntityManager entityManager);

    public  void create(T entity,EntityManager entityManager);

    public  T createEntity(T entity,EntityManager entityManager);

    public  T updateEntity(T entity,EntityManager entityManager);

}
