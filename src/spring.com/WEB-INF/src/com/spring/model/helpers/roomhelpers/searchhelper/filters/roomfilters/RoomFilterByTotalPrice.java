package com.spring.model.helpers.roomhelpers.searchhelper.filters.roomfilters;

import com.spring.model.entity.Room;
import com.spring.model.helpers.roomhelpers.searchhelper.filters.FilterWithTwoParam;

public class RoomFilterByTotalPrice implements FilterWithTwoParam<Room, Integer, Double> {
    @Override
    public boolean doFilter(Room entity, Integer param1, Double param2) {
        return (param2 >= ((entity.getPrice() * 100) * param1) / 100);
    }
}
