package com.spring.controllers.adminControllers;

import com.spring.model.entity.Order;
import com.spring.model.helpers.orderhelpers.IsRoomFreeInDates;
import com.spring.model.service.OrderService;
import com.spring.model.service.ReserveService;
import com.spring.model.service.exceptions.EntityNotFoundException;
import com.spring.model.service.exceptions.OrderNotFountException;
import com.spring.model.service.exceptions.OrderServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller("AdminOrderController")
@RequestMapping("admin/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @DeleteMapping(value = {"{id}"})
    public String cancelUserOrder(@PathVariable("id") long orderId, Map<String, Object> model) {
        try {
            orderService.delete(orderId);
            return "redirect:/admin/orders";
        } catch (OrderServiceException e) {
            model.put("error", "error");
            return "adminOrders";
        } catch (OrderNotFountException e) {
            model.put("error", "entity.not.exist");
            return "adminOrders";
        }
    }

    @GetMapping()
    public String showAdminOrdersList(Map<String, Object> model) {
        try {
            List<Order> orders = orderService.readAll();
            model.put("orders", orders);
            return "adminOrders";
        } catch (OrderServiceException e) {
            model.put("error", "error");
            return "adminOrders";
        }
    }

    @GetMapping(value = {"{id}"}, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOrder(@PathVariable("id") long id) {
        try {
            Order order = orderService.findById(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (OrderNotFountException e) {
            return new ResponseEntity<>("resource not found", HttpStatus.NOT_FOUND);
        } catch (OrderServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
