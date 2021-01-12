package com.spring.model.helpers.roomhelpers.datehelpers;

import com.spring.model.service.exceptions.ValidationException;

import java.sql.Date;
import java.sql.Timestamp;

public class DaysMaker {
    public int getDays(Timestamp dateIn, Timestamp dateOut) throws ValidationException {
        if (dateIn != null && dateOut != null) {
            StringBuilder inDate = new StringBuilder(dateIn.toString());
            StringBuilder outDate = new StringBuilder(dateOut.toString());
            int index = inDate.indexOf(" ");
            inDate.delete(index, inDate.length());
            index = outDate.indexOf(" ");
            outDate.delete(index, outDate.length());
            long dateInLong = Date.valueOf(inDate.toString()).getTime();
            long dateOutLong = Date.valueOf(outDate.toString()).getTime();
            return (int) (dateOutLong - dateInLong) / 86400000;
        } else throw new ValidationException("parameters mustn't be null");
    }

}
