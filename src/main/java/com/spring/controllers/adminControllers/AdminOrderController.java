package com.spring.controllers.adminControllers;

import com.spring.model.entity.Order;
import com.spring.model.helpers.orderhelpers.IsRoomFreeInDates;
import com.spring.model.service.OrderService;
import com.spring.model.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin/order")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;


    @DeleteMapping(value = {"{id}"})
    public String cancelUserOrder(@PathVariable("id") long orderId) {
        orderService.delete(orderId);
        return "redirect:/admin/order";
    }

    @GetMapping()
    public String showAdminOrdersList(Map<String, Object> model) {
        List<Order> orders = orderService.readAll();
        model.put("orders", orders);
        return "adminOrders";
    }

    @GetMapping(value = {"{id}"},produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getOrder(@PathVariable("id") Long id){
        try {
            Order order = orderService.findById(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
