package com.spring.model.helpers.pricehelpers;

import com.spring.model.helpers.roomhelpers.datehelpers.DaysMaker;
import com.spring.model.helpers.roomhelpers.datehelpers.TimestampMaker;
import com.spring.model.service.exceptions.ValidationException;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.Locale;

public class TotalPrice {
    private final DaysMaker daysMaker;
    private final TimestampMaker timestampMaker;

    public TotalPrice(DaysMaker daysMaker,TimestampMaker timestampMaker){
        this.timestampMaker = timestampMaker;
        this.daysMaker = daysMaker;
    }
    public String getTotalPrice(double pricePerDay, Timestamp dateIn, Timestamp dateOut) throws ValidationException {
        Locale locale = new Locale("en", "US");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        long days = daysMaker.getDays(dateIn,dateOut);
        if (days <= 1) {
            return numberFormat.format(pricePerDay);
        } else return numberFormat.format(days * pricePerDay);
    }

    public double getTotalPrice(double pricePerDay, String dateIn, String timeIn, String dateOut, String timeOut)
            throws ValidationException {
        Timestamp dayIn = timestampMaker.getTimestamp(dateIn,timeIn);
        Timestamp dayOut = timestampMaker.getTimestamp(dateOut,timeOut);
        long days = daysMaker.getDays(dayIn,dayOut);
        long pricePerDayLong = (long) (pricePerDay * 100);
        if (days <= 1) return pricePerDay;
        else return (double) (pricePerDayLong * days) / 100;
    }
}
