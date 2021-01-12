package com.spring.model.helpers.orderhelpers;


import com.spring.model.helpers.roomhelpers.datehelpers.TimestampMaker;
import com.spring.model.service.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;

public class OrderCorrectDate {
    private TimestampMaker timestampMaker;

    public OrderCorrectDate(TimestampMaker timestampMaker) {
        this.timestampMaker = timestampMaker;
    }

    public boolean isCorrectDates(String dateIn, String timeIn, String dateOut, String timeOut)
            throws ValidationException {
        long inDate = timestampMaker.getTimestamp(dateIn, timeIn).getTime();
        long outDate = timestampMaker.getTimestamp(dateOut, timeOut).getTime();
        long currentDate = new Date().getTime();
        return currentDate < inDate && inDate < outDate;
    }
}
