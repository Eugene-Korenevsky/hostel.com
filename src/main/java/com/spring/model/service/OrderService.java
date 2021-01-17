package com.spring.model.service;

import com.spring.model.entity.Order;
import com.spring.model.service.exceptions.EntityNotFoundException;
import com.spring.model.service.exceptions.OrderNotFountException;
import com.spring.model.service.exceptions.OrderServiceException;
import com.spring.model.service.exceptions.ValidationException;

import java.util.List;

public interface OrderService extends GenericService<Order> {

    public List<Order> findByUserId(long userId) throws OrderServiceException;
}
