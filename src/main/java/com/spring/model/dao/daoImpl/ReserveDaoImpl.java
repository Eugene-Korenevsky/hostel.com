package com.spring.model.dao.daoImpl;

import com.spring.model.dao.GenericDao;
import com.spring.model.dao.ReserveDao;
import com.spring.model.entity.Order;
import com.spring.model.entity.Reserve;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ReserveDaoImpl extends GenericDaoImpl<Reserve> implements ReserveDao {
    ReserveDaoImpl(){super(Reserve.class);}

    @Override
    public List<Reserve> findAllByUserId(long id, EntityManager entityManager) {
        TypedQuery<Reserve> query =
                entityManager.createNamedQuery("findReservesByUserId", Reserve.class).setParameter("USER_ID",id);
        return query.getResultList();
    }
}
