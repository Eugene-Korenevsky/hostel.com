package com.spring.model.helpers.roomhelpers.searchhelper.search.roomsearch;

import com.spring.model.entity.Room;
import com.spring.model.helpers.roomhelpers.datehelpers.DaysMaker;
import com.spring.model.helpers.roomhelpers.searchhelper.RoomSearchHelper;
import com.spring.model.helpers.roomhelpers.searchhelper.filters.Filter;
import com.spring.model.helpers.roomhelpers.searchhelper.filters.FilterWithTwoParam;
import com.spring.model.service.exceptions.ValidationException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RoomSearchBySitsAndPrice implements RoomSearchHelper {
    private final Filter<Room, Integer> filter;
    private final FilterWithTwoParam<Room, Integer, Double> filterWithTwoParam;
    private final DaysMaker daysMaker;

    public RoomSearchBySitsAndPrice(Filter<Room, Integer> filter, FilterWithTwoParam<Room, Integer, Double> filterWithTwoParam,
                                    DaysMaker daysMaker) {
        this.filter = filter;
        this.filterWithTwoParam = filterWithTwoParam;
        this.daysMaker = daysMaker;
    }

    @Override
    public List<Room> searchByTotalPriceAndSits(List<Room> rooms, Timestamp dateIn, Timestamp dateOut,
                                                double searchPrice, int sits) throws ValidationException {
        if (rooms != null && dateIn != null && dateOut != null) {
            List<Room> result = new ArrayList<>();
            int days = daysMaker.getDays(dateIn, dateOut);
            for (Room room : rooms) {
                if (filter.doFilter(room, sits) && filterWithTwoParam.doFilter(room, days, searchPrice)) {
                    result.add(room);
                }
            }
            return result;
        }else throw new ValidationException("parameters mustn't be null");
    }
}
