package com.spring.controllers.userControllers;

import com.spring.model.entity.Order;
import com.spring.model.entity.Reserve;
import com.spring.model.entity.User;
import com.spring.model.helpers.orderhelpers.IsRoomFreeInDates;
import com.spring.model.service.OrderService;
import com.spring.model.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ReserveController {
    @Value("${access.first}")
    private String access1;
    @Value("${access.second}")
    private String access2;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ReserveService reserveService;
    @Autowired
    private IsRoomFreeInDates isRoomFreeInDates;


    @RequestMapping(value = {"confirmReserve"})
    public String doReserve(HttpSession session, @RequestParam(value = "id") long orderId,
                            Map<String, Object> model) {
        User user = (User) session.getAttribute("user");
        if (user.getRole().getRole().equals(access1)) {
            Order order = orderService.findById(orderId);
            if (order != null && isRoomFreeInDates.isRoomFree(order.getRoom().getId(),
                    order.getDateIn(), order.getDateOut())) {
                reserveService.create(orderId);
                return "redirect:/findUserReserves";
            } else {
                model.put("message", "message.is.already.reserved");
                return "unCorrect";
            }

        } else return "loginForm";
    }

   /* @RequestMapping(value = {"deleteReserve"})
    public String deleteReserve(HttpSession session, @RequestParam(value = "id") long id) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            reserveService.delete(id);
            return "redirect:/findUserReserves";
        } else return "loginForm";
    }*/


}
