package com.spring.model.dao.daoImpl;

import com.spring.model.dao.GenericDao;
import com.spring.model.entity.Reserve;

import javax.persistence.EntityManager;
import java.util.List;

public class ReserveDao extends GenericDaoImpl<Reserve> {
    ReserveDao(){super(Reserve.class);}
}
