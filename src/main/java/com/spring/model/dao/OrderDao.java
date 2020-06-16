package com.spring.model.dao.daoImpl;

import com.spring.model.dao.GenericDao;
import com.spring.model.entity.Order;

import java.util.List;

public interface OrderDao1 extends GenericDao<Order> {
    public List<Order> findOrdersByUserId(long userId);
}
