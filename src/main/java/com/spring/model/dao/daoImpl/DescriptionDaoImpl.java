package com.spring.model.dao.daoImpl;

import com.spring.model.dao.DescriptionDao;
import com.spring.model.dao.GenericDao;
import com.spring.model.entity.Description;

import javax.persistence.EntityManager;
import java.util.List;

public class DescriptionDaoImpl extends GenericDaoImpl<Description> implements DescriptionDao {


    protected DescriptionDaoImpl() {
        super(Description.class);
    }
}
