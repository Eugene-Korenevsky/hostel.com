package com.spring.model.service;

import com.spring.model.entity.Order;
import com.spring.model.service.exceptions.EntityNotFoundException;
import com.spring.model.service.exceptions.OrderNotFountException;
import com.spring.model.service.exceptions.OrderServiceException;
import com.spring.model.service.exceptions.ValidationException;

import java.util.List;

public interface OrderService {
    public Order findById(long id) throws OrderServiceException, OrderNotFountException;

    public List<Order> readAll() throws OrderServiceException;

    public List<Order> findByUserId(long userId) throws OrderServiceException;

    public Order create(Order order) throws OrderServiceException, ValidationException;

    public void delete(long id) throws OrderServiceException,OrderNotFountException;

    public Order update(Order order) throws OrderServiceException,ValidationException,OrderNotFountException;
}
