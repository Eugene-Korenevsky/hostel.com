package com.spring.controllers.userControllers;

import com.spring.model.entity.Order;
import com.spring.model.entity.Room;
import com.spring.model.entity.User;
import com.spring.model.helpers.orderhelpers.IsRoomFreeInDates;
import com.spring.model.helpers.orderhelpers.OrderCorrectDate;
import com.spring.model.helpers.pricehelpers.TotalPrice;
import com.spring.model.helpers.roomhelpers.datehelpers.TimestampMaker;
import com.spring.model.service.*;
import com.spring.model.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@Controller
@RequestMapping("orders")
public class OrderController {
    @Value("${default.order.time}")
    private String defaultOrderTime;
    @Autowired
    private OrderService orderService;
    @Autowired
    private IsRoomFreeInDates isRoomFreeInDates;
    @Autowired
    private TimestampMaker timestampMaker;
    @Autowired
    private UserService userService;
    @Autowired
    private TotalPrice totalPrice;
    @Autowired
    private OrderCorrectDate orderCorrectDate;
    @Autowired
    private RoomService roomService;

    @DeleteMapping(value = {"{id}"})
    public ResponseEntity<?> deleteOrder(@PathVariable("id") long id) {
        try {
            orderService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("resource not found", HttpStatus.NOT_FOUND);
        } catch (ServiceException e) {
            return new ResponseEntity<>("something wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> makeOrder(@RequestParam("dateIn") String dateIn,
                                       @RequestParam("dateOut") String dateOut,
                                       @RequestParam("roomId") long id) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        org.springframework.security.core.userdetails.User user =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        if (user != null) {
            try {
                User user1 = userService.login(user.getUsername(), user.getPassword());
                Timestamp inDate = timestampMaker.getTimestamp(dateIn, defaultOrderTime);
                Timestamp outDate = timestampMaker.getTimestamp(dateOut, defaultOrderTime);
                try {
                    Room room = roomService.readById(id, false);
                    if (isRoomFreeInDates.isRoomFree(room, inDate, outDate)) {
                        double priceDouble = totalPrice.getTotalPrice(room.getPrice(), dateIn,
                                defaultOrderTime, dateOut, defaultOrderTime);
                        Order order = new Order();
                        order.setTotalPrice(priceDouble);
                        order.setDateIn(inDate);
                        order.setDateOut(outDate);
                        order.setRoom(room);
                        order.setUser(user1);
                        orderService.create(order);
                        return new ResponseEntity<>(order, HttpStatus.CREATED);
                    } else return new ResponseEntity<>("room is not free in this dates", HttpStatus.OK);
                } catch (EntityNotFoundException e) {
                    return new ResponseEntity<>("room not exist", HttpStatus.NOT_FOUND);
                } catch (ServiceException | RoomServiceException | ReserveServiceException e) {
                    return new ResponseEntity<>("server error", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } catch (UserServiceException e) {
                return new ResponseEntity<>("serverError", HttpStatus.INTERNAL_SERVER_ERROR);
            } catch (ValidationException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else return new ResponseEntity<>("user must login", HttpStatus.FORBIDDEN);
    }

    @PostMapping(value = {"isFree"}, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> makePrice(@RequestParam("dateIn") String dateIn,
                                       @RequestParam("dateOut") String dateOut,
                                       @RequestParam("roomId") long id) {
        StringBuilder result = new StringBuilder();
        try {
            Timestamp inDate = timestampMaker.getTimestamp(dateIn, defaultOrderTime);
            Timestamp outDate = timestampMaker.getTimestamp(dateOut, defaultOrderTime);
            Room room = roomService.readById(id, false);
            if (orderCorrectDate.isCorrectDates(dateIn, defaultOrderTime, dateOut, defaultOrderTime)) {
                if (isRoomFreeInDates.IsRoomFree(room, dateIn, defaultOrderTime, dateOut, defaultOrderTime)) {
                    String price = totalPrice.getTotalPrice(room.getPrice(), inDate, outDate);
                    result.append(price);
                } else {
                    result.append("notFree");
                }
            } else {
                result.append("wrongDates");
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>("Date format must be yyyy-mm-dd", HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("room is not exist", HttpStatus.NOT_FOUND);
        } catch (RoomServiceException | ReserveServiceException e) {
            return new ResponseEntity<>("server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
