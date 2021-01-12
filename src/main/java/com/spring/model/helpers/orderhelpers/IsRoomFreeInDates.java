package com.spring.model.helpers.orderhelpers;

import com.spring.model.entity.Reserve;
import com.spring.model.entity.Room;
import com.spring.model.helpers.roomhelpers.datehelpers.TimestampMaker;
import com.spring.model.service.ReserveService;
import com.spring.model.service.exceptions.ReserveServiceException;
import com.spring.model.service.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;

public class IsRoomFreeInDates {
    private final TimestampMaker timestampMaker;
    private final ReserveService reserveService;

    public IsRoomFreeInDates(TimestampMaker timestampMaker, ReserveService reserveService) {
        this.reserveService = reserveService;
        this.timestampMaker = timestampMaker;
    }

    public boolean IsRoomFree(Room room, String dateIn, String timeIn, String dateOut, String timeOut)
            throws ReserveServiceException, ValidationException {
        Timestamp timestamp = timestampMaker.getTimestamp(dateIn, timeIn);
        Timestamp timestamp1 = timestampMaker.getTimestamp(dateOut, timeOut);
        return isRoomFree(room,timestamp,timestamp1);
    }

    public boolean isRoomFree(Room room, Timestamp dateIn, Timestamp dateOut) throws ReserveServiceException,ValidationException {
        List<Reserve> reserves = reserveService.findByDatesIntervalAndRoom(dateIn, dateOut, room);
        return reserves.size() < 1;
    }
}
