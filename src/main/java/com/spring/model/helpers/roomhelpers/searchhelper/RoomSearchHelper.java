package com.spring.model.helpers.roomhelpers.searchhelper;

import com.spring.model.entity.Room;
import com.spring.model.service.exceptions.ValidationException;

import java.sql.Timestamp;
import java.util.List;

public interface RoomSearchHelper {
    public List<Room> searchByTotalPriceAndSits(List<Room> rooms, Timestamp dateIn, Timestamp dateOut,
                                                double searchPrice,int sits) throws ValidationException;
}
