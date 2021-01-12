package com.spring.model.helpers.roomhelpers.datehelpers;

import com.spring.model.service.exceptions.ValidationException;

import java.sql.Timestamp;

public class TimestampMaker {
    public Timestamp getTimestamp(String day, String time) throws ValidationException {
        if (day != null && time != null)
            return Timestamp.valueOf(day + " " + time + ":00");
        else throw new ValidationException("parameters mustn't be null");
    }
}
