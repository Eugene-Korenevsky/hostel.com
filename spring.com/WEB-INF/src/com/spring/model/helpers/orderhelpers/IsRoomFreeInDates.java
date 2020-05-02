package com.spring.model.helpers.orderhelpers;

import com.spring.model.entity.Reserve;
import com.spring.model.helpers.roomhelpers.datehelpers.TimestampMaker;
import com.spring.model.service.MainServiceFactory;
import com.spring.model.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;

public class IsRoomFreeInDates {
    private final TimestampMaker timestampMaker;
    private final ReserveService reserveService;

    public IsRoomFreeInDates(TimestampMaker timestampMaker,ReserveService reserveService){
        this.reserveService = reserveService;
        this.timestampMaker = timestampMaker;
    }
    public boolean IsRoomFree(long roomId, String dateIn, String timeIn, String dateOut, String timeOut) {
        boolean found = false;
        long inDate = timestampMaker.getTimestamp(dateIn,timeIn).getTime();
        long outDate = timestampMaker.getTimestamp(dateOut,timeOut).getTime();
        return isFree(inDate, outDate, roomId);
    }

    public boolean isRoomFree(long roomId, Timestamp dateIn, Timestamp dateOut) {
        boolean found = false;
        long inDate = dateIn.getTime();
        long outDate = dateOut.getTime();
        return isFree(inDate, outDate, roomId);

    }

    private boolean isFree(long inDate, long outDate,long roomId) {
        boolean found = false;
        List<Reserve> reserves = reserveService.readAll();
        for (Reserve reserve : reserves) {
            if (roomId == reserve.getRoom().getId()) {
                long reserveDateIn = reserve.getDateIn().getTime();
                long reserveDateOut = reserve.getDateOut().getTime();
                if ((inDate >= reserveDateIn && inDate <= reserveDateOut) ||
                        (outDate >= reserveDateIn && outDate <= reserveDateOut)) {
                    found = true;
                    break;
                }
            }
        }
        return !found;
    }
}
