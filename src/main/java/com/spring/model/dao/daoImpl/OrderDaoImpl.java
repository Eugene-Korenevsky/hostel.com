package com.spring.model.dao.daoImpl;

import com.spring.model.dao.GenericDao;
import com.spring.model.dao.OrderDao;
import com.spring.model.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

    OrderDaoImpl() {
        super(Order.class);
    }


    @Override
    public List<Order> findOrdersByUserId(long userId, EntityManager entityManager) {
        TypedQuery<Order> query = entityManager.createQuery(
                "select i from Order i where USER_ID = ?1", Order.class
        );
        query.setParameter(1, userId);
        return query.getResultList();
    }
}
