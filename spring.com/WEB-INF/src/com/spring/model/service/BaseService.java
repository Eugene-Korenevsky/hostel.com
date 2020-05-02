package com.spring.model.service;

import com.spring.model.dao.GenericDao;

import javax.persistence.EntityManager;

public class BaseService {
    private EntityManager entityManager;
    private GenericDao genericDao;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected  GenericDao getGenericDao() {
        return genericDao;
    }

    public void setGenericDao(GenericDao genericDao) {
        this.genericDao = genericDao;
    }
}
