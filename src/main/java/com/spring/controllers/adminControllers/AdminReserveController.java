package com.spring.controllers.adminControllers;

import com.spring.model.entity.Order;
import com.spring.model.entity.Reserve;
import com.spring.model.entity.Room;
import com.spring.model.helpers.orderhelpers.IsRoomFreeInDates;
import com.spring.model.helpers.pricehelpers.TotalPrice;
import com.spring.model.helpers.roomhelpers.datehelpers.TimestampMaker;
import com.spring.model.service.OrderService;
import com.spring.model.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin/reserves")
public class AdminReserveController {
    @Autowired
    private ReserveService reserveService;
    @Autowired
    private IsRoomFreeInDates isRoomFreeInDates;
    @Autowired
    private OrderService orderService;

    @PostMapping(value = {"isFree"}, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<StringBuilder> makePrice(@RequestParam("dateIn") long dateIn,
                                                   @RequestParam("dateOut") long dateOut,
                                                   @RequestParam("roomId") long id,
                                                   @RequestParam("orderId") long orderId) {
        try {
            StringBuilder result = new StringBuilder();
            Timestamp inDate = new Timestamp(dateIn);
            Timestamp outDate = new Timestamp(dateOut);
            System.out.println(inDate);
            System.out.println(outDate);
            if (isRoomFreeInDates.isRoomFree(id, inDate, outDate)) {
                Order order = orderService.findById(orderId);
                double price = order.getTotalPrice();
                result.append(price);
            } else {
                result.append("notFree");
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reserve> createReserve(@RequestBody Reserve reserve) {
        try {
            reserveService.create(reserve.getId());
            return new ResponseEntity<>(reserve, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping(value = {"{id}"})
    public String cancelUserReserve(@PathVariable("id") long reserveId) {
        reserveService.delete(reserveId);
        return "redirect:/admin/reserve";
    }

    @GetMapping()
    public String showAllReserves(Map<String, Object> model) {
        List<Reserve> reserves = reserveService.readAll();
        model.put("reserves", reserves);
        return "adminReserves";
    }
}
