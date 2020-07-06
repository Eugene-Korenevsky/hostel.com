package com.spring.controllers;

import com.spring.model.entity.Description;
import com.spring.model.entity.Room;
import com.spring.model.entity.User;
import com.spring.model.helpers.orderhelpers.OrderCorrectDate;
import com.spring.model.helpers.roomhelpers.datehelpers.TimestampMaker;
import com.spring.model.helpers.roomhelpers.searchhelper.RoomSearchHelper;
import com.spring.model.service.DescriptionService;
import com.spring.model.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

@Controller
public class RoomController {
    @Value("${access.first}")
    private String access1;
    @Value("${access.second}")
    private String access2;
    @Autowired
    private DescriptionService descriptionService;
    @Autowired
    private TimestampMaker timestampMaker;
    @Autowired
    private OrderCorrectDate orderCorrectDate;
    @Autowired
    private RoomSearchHelper roomSearchHelper;
    @Autowired
    private RoomService roomService;


    @RequestMapping(value = {"roomsList"}, method = RequestMethod.GET)
    public String showRoomList(Map<String, Object> model) {
        List<Room> rooms = roomService.readAll(false);
        model.put("roomList", rooms);
        return "rooms";
    }
    @RequestMapping(value = {"roomsListAjax"},
            method = RequestMethod.GET,produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<String>  showRoomListAjax(){
        try {
            //List<Room> rooms = roomService.readAll(false);
           // ResponseEntity<List<Room>> responseEntity = new ResponseEntity<List<Room>>(rooms, HttpStatus.OK);
            ResponseEntity<String> responseEntity = new ResponseEntity<String>("hello from server",
                    HttpStatus.OK);
            System.out.println("ok");
            //return responseEntity;
            return responseEntity;
        }catch (Exception e){
            System.out.println("error");
            System.out.println(e.getMessage());
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
            //return new ResponseEntity<List<Room>>(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = {"roomsListAjax1/{id}"},
            method = RequestMethod.GET,produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<Room>  showRoomListAjax1(@PathVariable("id") Long id){
        try {
            Room room = roomService.readById(id,true);
            ResponseEntity<Room> responseEntity = new ResponseEntity<Room>(room,HttpStatus.OK);
            return responseEntity;
        }catch (Exception e){
            return new ResponseEntity<Room>(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = {"findRoom"}, method = RequestMethod.GET)
    public String showRoom(Map<String, Object> model,
                           @RequestParam(value = "id") long id) {
        Room room = roomService.readById(id,true);
        model.put("room", room);
        return "roomFind";
    }

    @RequestMapping(value = {"addRoomForm"}, method = RequestMethod.GET)
    public String roomForm() {
        return "createRoomForm";
    }

    @RequestMapping(value = {"deleteRoom"}, method = RequestMethod.POST)
    public String deleteRoom(HttpSession session, @RequestParam(value = "id") long roomId) {
        User user = (User) session.getAttribute("user");
        if (user.getRole().getRole().equals(access1)) {
            roomService.delete(roomId);
            return "redirect:/roomsList";
        } else return "loginForm";
    }

    @RequestMapping(value = {"changeDescriptions"}, method = RequestMethod.POST)
    public String changeDescriptionsForm(HttpSession session, @RequestParam(value = "roomId") long roomId,
                                         Map<String, Object> model) {
        User user = (User) session.getAttribute("user");
        if (user.getRole().getRole().equals(access1)) {
            Room room = roomService.readById(roomId,true);
            List<Description> descriptions = descriptionService.readAll(false);
            model.put("descriptions", descriptions);
            session.setAttribute("room", room);
            session.setAttribute("roomId", roomId);
            return "changeDescription";
        } else return "loginForm";
    }

    @RequestMapping(value = {"changeRoomForm"}, method = RequestMethod.POST)
    public String changeRoomForm(HttpSession session, @RequestParam(value = "roomId") long roomId) {
        User user = (User) session.getAttribute("user");
        if (user.getRole().getRole().equals(access1) ) {
            session.setAttribute("roomId", roomId);
            return "changeRoom";
        } else return "loginForm";
    }

    @RequestMapping(value = {"addDescription"}, method = RequestMethod.POST)
    public String addDescription(HttpSession session, @RequestParam(value = "descriptionId") long descriptionId) {
        User user = (User) session.getAttribute("user");
        if (user.getRole().getRole().equals(access1)) {
            Room room = (Room) session.getAttribute("room");
            if (room != null) {
                roomService.addDescription(room.getId(), descriptionId);
            }
            return "redirect:/roomsList";
        } else return "loginForm";
    }

    @RequestMapping(value = {"removeDescription"}, method = RequestMethod.POST)
    public String removeDescription(HttpSession session, @RequestParam(value = "descriptionId") long descriptionId) {
        User user = (User) session.getAttribute("user");
        if (user.getRole().getRole().equals(access1)) {
            Room room = (Room) session.getAttribute("room");
            if (room != null) {
                roomService.removeDescription(room.getId(), descriptionId);
            }
            return "redirect:/roomsList";
        } else return "loginForm";
    }

    @RequestMapping(value = {"createRoom"}, method = RequestMethod.POST)
    public String createRoom(HttpSession session, @RequestParam(value = "class") String roomClass,
                             @RequestParam(value = "price") String price, @RequestParam(value = "number") int number,
                             @RequestParam(value = "sits") int sits, Map<String, Object> model) {
        User user = (User) session.getAttribute("user");
        if (user.getRole().getRole().equals(access1)) {
            try {
                double price1 = Double.parseDouble(price);
                if (sits >= 1) {
                    roomService.create(price1, roomClass, sits, number);
                    return "redirect:/roomsList";
                } else {
                    model.put("message", "wrong.dates");
                    return "createRoomForm";
                }
            } catch (Exception e) {
                model.put("message", "wrong.dates");
                return "createRoomForm";
            }
        } else return "loginForm";
    }

    @RequestMapping(value = {"searchRoom"}, method = RequestMethod.POST)
    public String searchRoom(@RequestParam(value = "dayIn") String dayIn, @RequestParam(value = "timeIn") String timeIn,
                             @RequestParam(value = "dayOut") String dayOut, @RequestParam(value = "timeOut") String timeOut,
                             @RequestParam(value = "sits") int sits, @RequestParam(value = "price") String price,
                             Map<String, Object> model) {
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


    }

    @RequestMapping(value = {"changeRoom"}, method = RequestMethod.POST)
    public String changeRoom(@RequestParam(value = "roomId") long roomId, @RequestParam(value = "class") String roomClass,
                             @RequestParam(value = "number") int number, @RequestParam(value = "price") String price,
                             @RequestParam(value = "sits") int sits, Map<String, Object> model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user.getRole().getRole().equals(access1)) {
            try {
                double price1 = Double.parseDouble(price);
                if (sits >= 1) {
                    roomService.update(roomId, price1, roomClass, sits, number);
                    return "redirect:/roomsList";
                } else {
                    model.put("message", "wrong.dates");
                    return "createRoomForm";
                }
            } catch (Exception e) {
                model.put("message", "wrong.dates");
                return "createRoomForm";
            }
        } else return "loginForm";

    }
}
