package com.spring.model.dao.daoImpl;

import com.spring.model.dao.DescriptionDao;
import com.spring.model.dao.GenericDao;
import com.spring.model.entity.Description;
import com.spring.model.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DescriptionDaoImpl extends GenericDaoImpl<Description> implements DescriptionDao {


    protected DescriptionDaoImpl() {
        super(Description.class);
    }

    @Override
    public Description findDescriptionByName(String name,EntityManager entityManager) {
        TypedQuery<Description> query =
                entityManager.createNamedQuery("descByName", Description.class);
        query.setParameter("description", name);
        return query.getSingleResult();
    }
}
