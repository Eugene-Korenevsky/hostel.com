package com.spring.model.dao.daoImpl;

import com.spring.model.dao.GenericDao;
import com.spring.model.entity.Description;

import javax.persistence.EntityManager;
import java.util.List;

public class DescriptionDao extends GenericDaoImpl<Description> {


    protected DescriptionDao() {
        super(Description.class);
    }
}
