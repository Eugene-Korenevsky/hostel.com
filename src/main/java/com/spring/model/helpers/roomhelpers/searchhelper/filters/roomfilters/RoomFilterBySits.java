package com.spring.model.helpers.roomhelpers.searchhelper.filters.roomfilters;

import com.spring.model.entity.Room;
import com.spring.model.helpers.roomhelpers.searchhelper.filters.Filter;
import com.spring.model.service.exceptions.ValidationException;

public class RoomFilterBySits implements Filter<Room, Integer> {

    @Override
    public synchronized boolean doFilter(Room room, Integer param) throws ValidationException {
        if (room != null && param != null) {
            int searchSits = param;
            return (searchSits == room.getSits());
        } else throw new ValidationException("parameters mustn't be null");
    }


}
