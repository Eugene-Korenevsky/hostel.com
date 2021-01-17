package com.spring.model.service.exceptions;

public class OrderNotFountException extends EntityNotFoundException{
    public OrderNotFountException(String message) {
        super(message);
    }
}
