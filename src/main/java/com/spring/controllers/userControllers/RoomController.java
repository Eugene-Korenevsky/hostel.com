package com.spring.controllers.userControllers;

import com.spring.model.entity.*;
import com.spring.model.helpers.orderhelpers.OrderCorrectDate;
import com.spring.model.helpers.roomhelpers.datehelpers.TimestampMaker;
import com.spring.model.helpers.roomhelpers.searchhelper.RoomSearchHelper;
import com.spring.model.service.RoomService;
import com.spring.model.service.exceptions.EntityNotFoundException;
import com.spring.model.service.exceptions.RoomServiceException;
import com.spring.model.service.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping("rooms")
public class RoomController {
    @Autowired
    private TimestampMaker timestampMaker;
    @Autowired
    private OrderCorrectDate orderCorrectDate;
    @Autowired
    private RoomSearchHelper roomSearchHelper;
    @Autowired
    private RoomService roomService;

    @GetMapping()
    public String showRoomList(Map<String, Object> model) {
        try {
            model.put("roomList", roomService.readAll(true));
            return "roomList";
        } catch (RoomServiceException e) {
            model.put("error", "error");
            return "roomList";
        }
    }


    @GetMapping(value = {"{id}"})
    public String showRoom(Map<String, Object> model, @PathVariable("id") long id) {
        try {
            Room room = roomService.readById(id, true);
            model.put("room", room);
            return "room";
        } catch (RoomServiceException e) {
            model.put("error", "error");
            return "room";
        } catch (EntityNotFoundException e) {
            model.put("error", "entity.not.exist");
            return "room";
        }

    }


    @RequestMapping(value = {"searchRoom"}, method = RequestMethod.POST)
    public String searchRoom(@RequestParam(value = "dayIn") String dayIn, @RequestParam(value = "timeIn") String timeIn,
                             @RequestParam(value = "dayOut") String dayOut, @RequestParam(value = "timeOut") String timeOut,
                             @RequestParam(value = "sits") int sits, @RequestParam(value = "price") String price,
                             Map<String, Object> model) {
        try {
            if (orderCorrectDate.isCorrectDates(dayIn, timeIn, dayOut, timeOut)) {
                try {
                    double price1 = Double.parseDouble(price);
                    List<Room> rooms = roomService.readAll(false);
                    Timestamp dateIn = timestampMaker.getTimestamp(dayIn, timeIn);
                    Timestamp dateOut = timestampMaker.getTimestamp(dayOut, timeOut);
                    List<Room> result = roomSearchHelper.searchByTotalPriceAndSits(rooms, dateIn, dateOut, price1, sits);
                    model.put("roomList", result);
                    return "rooms";
                } catch (Exception e) {
                    model.put("message", "unCorrect.dates");
                    return "unCorrect";
                }
            } else {
                model.put("message", "unCorrect.dates");
                return "unCorrect";
            }
        } catch (ValidationException e) {
            model.put("message", "unCorrect.dates");
            return "unCorrect";
        }
    }
}
