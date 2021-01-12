package com.spring.model.helpers.roomhelpers.searchhelper.filters.roomfilters;

import com.spring.model.entity.Room;
import com.spring.model.helpers.roomhelpers.searchhelper.filters.FilterWithTwoParam;
import com.spring.model.service.exceptions.ValidationException;

public class RoomFilterByTotalPrice implements FilterWithTwoParam<Room, Integer, Double> {
    @Override
    public boolean doFilter(Room entity, Integer param1, Double param2) throws ValidationException {
        if (entity != null && param1 != null && param2 != null)
            return (param2 >= ((entity.getPrice() * 100) * param1) / 100);
        else throw new ValidationException("parameters mustn't be null");
    }
}
