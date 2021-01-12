package com.spring.model.service.exceptions;

public class RoomWithThisNumberIsExist extends Exception {
    public RoomWithThisNumberIsExist(String message) {
        super(message);
    }
}
