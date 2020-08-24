package com.spring.model.dao;

import com.spring.model.dao.daoImpl.GenericDaoImpl;
import com.spring.model.entity.Description;

import javax.persistence.EntityManager;

public interface DescriptionDao extends GenericDao<Description> {
   public Description findDescriptionByName(String name, EntityManager entityManager);

}
