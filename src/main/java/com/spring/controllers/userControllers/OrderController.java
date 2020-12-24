package com.spring.controllers.userControllers;

import com.spring.model.entity.Order;
import com.spring.model.entity.Reserve;
import com.spring.model.entity.Room;
import com.spring.model.entity.User;
import com.spring.model.helpers.orderhelpers.IsRoomFreeInDates;
import com.spring.model.helpers.orderhelpers.OrderCorrectDate;
import com.spring.model.helpers.pricehelpers.TotalPrice;
import com.spring.model.helpers.roomhelpers.datehelpers.TimestampMaker;
import com.spring.model.service.*;
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

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("orders")
public class OrderController {
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
   public ResponseEntity<Order> deleteOrder(@PathVariable("id") long id){
       try {
           orderService.delete(id);
           return new ResponseEntity<>(HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
   }


    @PostMapping(produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> makeOrder(@RequestParam("dateIn") String dateIn,
                                           @RequestParam("dateOut") String dateOut,
                                           @RequestParam("roomId") long id) {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        org.springframework.security.core.userdetails.User user =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        try {
            User user1 = userService.login(user.getUsername(), user.getPassword());
            Timestamp inDate = timestampMaker.getTimestamp(dateIn, "12:00");
            Timestamp outDate = timestampMaker.getTimestamp(dateOut, "12:00");
            Room room = roomService.readById(id, false);
            double priceDouble = totalPrice.getTotalPrice(room.getPrice(), dateIn,
                    "12:00", dateOut, "12:00");
            Order order = new Order();
            order.setTotalPrice(priceDouble);
            order.setDateIn(inDate);
            order.setDateOut(outDate);
            order.setRoom(room);
            order.setUser(user1);
            orderService.create(order);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = {"isFree"}, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<StringBuilder> makePrice(@RequestParam("dateIn") String dateIn,
                                                   @RequestParam("dateOut") String dateOut,
                                                   @RequestParam("roomId") long id,
                                                   HttpSession session) {
        try {
            StringBuilder result = new StringBuilder();
            Timestamp inDate = timestampMaker.getTimestamp(dateIn, "12:00");
            Timestamp outDate = timestampMaker.getTimestamp(dateOut, "12:00");
            Room room = roomService.readById(id, false);
            if (orderCorrectDate.isCorrectDates(dateIn, "12:00", dateOut, "12:00")) {
                if (isRoomFreeInDates.IsRoomFree(id, dateIn, "12:00", dateOut, "12:00")) {
                    String price = totalPrice.getTotalPrice(room.getPrice(), inDate, outDate);
                    result.append(price);
                } else {
                    result.append("notFree");
                }
            } else {
                result.append("wrongDates");
            }
            return new ResponseEntity<>(result, HttpStatus.OK);//responseEntity;
        } catch (Exception e) {
            return new ResponseEntity<StringBuilder>(HttpStatus.BAD_REQUEST);
        }
    }

}
