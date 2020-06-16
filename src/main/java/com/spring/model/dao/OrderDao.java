package com.spring.model.dao;

import com.spring.model.dao.GenericDao;
import com.spring.model.entity.Order;

import javax.persistence.EntityManager;
import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    public List<Order> findOrdersByUserId(long userId, EntityManager entityManager);
}
