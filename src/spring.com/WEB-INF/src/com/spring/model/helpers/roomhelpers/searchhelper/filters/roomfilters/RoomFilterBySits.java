package com.spring.model.helpers.roomhelpers.searchhelper.filters.roomfilters;

import com.spring.model.entity.Room;
import com.spring.model.helpers.roomhelpers.searchhelper.filters.Filter;

public class RoomFilterBySits implements Filter<Room, Integer> {

    @Override
    public synchronized boolean doFilter(Room room, Integer param) {
        int searchSits = param;
        return (searchSits == room.getSits());
    }


}
