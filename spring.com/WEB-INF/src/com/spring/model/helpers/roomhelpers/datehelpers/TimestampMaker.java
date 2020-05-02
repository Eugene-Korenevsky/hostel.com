package com.spring.model.helpers.roomhelpers.datehelpers;

import java.sql.Timestamp;

public class TimestampMaker {
    public Timestamp getTimestamp(String day, String time) {
        return Timestamp.valueOf(day + " " + time + ":00");
    }
}
