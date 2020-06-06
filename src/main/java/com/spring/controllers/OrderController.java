package com.spring.controllers;

import com.spring.model.entity.Order;
import com.spring.model.entity.Room;
import com.spring.model.entity.User;
import com.spring.model.helpers.orderhelpers.IsRoomFreeInDates;
import com.spring.model.helpers.orderhelpers.OrderCorrectDate;
import com.spring.model.helpers.pricehelpers.TotalPrice;
import com.spring.model.helpers.roomhelpers.datehelpers.TimestampMaker;
import com.spring.model.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {
    @Value("${access.first}")
    private String access1;
    @Value("${access.second}")
    private String access2;
    @Autowired
    private RoomService roomService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderCorrectDate orderCorrectDate;
    @Autowired
    private IsRoomFreeInDates isRoomFreeInDates;
    @Autowired
    private TotalPrice totalPrice;
    @Autowired
    private TimestampMaker timestampMaker;


    @RequestMapping(value = {"findUserOrders"}, method = RequestMethod.GET)
    public String showUserOrders(Map<String, Object> model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        List<Order> orders;
        if (user != null) {
            if (user.getRole().getRole().equals(access2)) {
                orders = orderService.findByUserId(user.getId());
                model.put("orders", orders);
                return "orders";
            } else if(user.getRole().getRole().equals(access1)) {
                orders = orderService.readAll();
                model.put("orders", orders);
                return "orders";
            }else {
                return "loginForm";
            }
        } else return "loginForm";
    }

    @RequestMapping(value = {"makeOrder"}, method = RequestMethod.POST)
    public String makeOrder(Map<String, Object> model,
                            @RequestParam(value = "dateIn") String dateIn,
                            @RequestParam(value = "timeIn") String timeIn,
                            @RequestParam(value = "dateOut") String dateOut,
                            @RequestParam(value = "timeOut") String timeOut,
                            @RequestParam(value = "roomId") long roomId,
                            HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            if (orderCorrectDate.isCorrectDates(dateIn, timeIn, dateOut, timeOut)) {
                if (isRoomFreeInDates.IsRoomFree(roomId, dateIn, timeIn, dateOut, timeOut)) {
                    Order order = new Order();
                    order.setUser(user);
                    Room room = roomService.readById(roomId,false);
                    order.setRoom(room);
                    Timestamp inDate = timestampMaker.getTimestamp(dateIn, timeIn);
                    Timestamp outDate = timestampMaker.getTimestamp(dateOut, timeOut);
                    order.setDateIn(inDate);
                    order.setDateOut(outDate);
                    session.setAttribute("order", order);
                    model.put("price", totalPrice.getTotalPrice(room.getPrice(), inDate, outDate));
                    return "dates";
                } else {
                    model.put("message", "message.is.already.reserved");
                    return "unCorrect";
                }
            } else {
                model.put("message", "message.uncorrect.dates");
                return "unCorrect";
            }
        } else {
            return "loginForm";
        }
    }

    @RequestMapping(value = {"confirmOrder"},method = RequestMethod.POST)
    public String confirmOrder(HttpSession session) {
        User user = (User) session.getAttribute("user");
        Order order = (Order) session.getAttribute("order");
        if (user.getRole() != null) {
            orderService.create(order);
            return "redirect:/findUserOrders";
        } else return "loginForm";
    }

    @RequestMapping(value = {"deleteOrder"})
    public String deleteOrder(@RequestParam(value = "id") long id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            orderService.delete(id);
            return "redirect:/findUserOrders";
        } else return "loginForm";
    }

}
