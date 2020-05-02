package com.spring.model.service;

import com.spring.model.entity.Order;

import java.util.List;

public interface OrderService {
    public Order findById(long id);

    public List<Order> readAll();

    public List<Order> findByUserId(long userId);

    public void create(Order order);

    public void delete(long id);

    public void update(Order order);
}
