package com.spring.controllers.adminControllers;

import com.spring.model.entity.Order;
import com.spring.model.entity.Reserve;
import com.spring.model.entity.Room;
import com.spring.model.helpers.orderhelpers.IsRoomFreeInDates;
import com.spring.model.helpers.pricehelpers.TotalPrice;
import com.spring.model.helpers.roomhelpers.datehelpers.TimestampMaker;
import com.spring.model.service.OrderService;
import com.spring.model.service.ReserveService;
import com.spring.model.service.RoomService;
import com.spring.model.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Controller("AdminReserveController")
@RequestMapping("admin/reserves")
public class ReserveController {
    @Autowired
    private ReserveService reserveService;
    @Autowired
    private IsRoomFreeInDates isRoomFreeInDates;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RoomService roomService;

    @PostMapping(value = {"isFree"}, produces = {MimeTypeUtils.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> makePrice(@RequestParam("dateIn") long dateIn,
                                       @RequestParam("dateOut") long dateOut,
                                       @RequestParam("roomId") long id,
                                       @RequestParam("orderId") long orderId) {
        StringBuilder result = new StringBuilder();
        Timestamp inDate = new Timestamp(dateIn);
        Timestamp outDate = new Timestamp(dateOut);
        try {
            Room room = roomService.readById(id, false);
            try {
                if (isRoomFreeInDates.isRoomFree(room, inDate, outDate)) {
                    try {
                        Order order = orderService.findById(orderId);
                        double price = order.getTotalPrice();
                        result.append(price);
                    } catch (ServiceException e) {
                        return new ResponseEntity<>("server error", HttpStatus.INTERNAL_SERVER_ERROR);
                    } catch (EntityNotFoundException e) {
                        return new ResponseEntity<>("order not exist", HttpStatus.NOT_FOUND);
                    }
                } else {
                    result.append("notFree");
                }
            } catch (ValidationException e) {
                return new ResponseEntity<>("error date format",
                        HttpStatus.BAD_REQUEST);
            } catch (ReserveServiceException e) {
                return new ResponseEntity<>("server error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("room is not exist", HttpStatus.NOT_FOUND);
        } catch (RoomServiceException e) {
            return new ResponseEntity<>("server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createReserve(@RequestBody Reserve reserve) {
        if (reserve != null) {
            try {
                if (isRoomFreeInDates.isRoomFree(reserve.getRoom(), reserve.getDateIn(), reserve.getDateOut())) {
                    reserveService.create(reserve.getId());
                    return new ResponseEntity<>(reserve, HttpStatus.CREATED);
                } else return new ResponseEntity<>("room is not free in dates", HttpStatus.OK);
            } catch (EntityNotFoundException e) {
                return new ResponseEntity<>("order isn't exist", HttpStatus.NOT_FOUND);
            } catch (ReserveServiceException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            } catch (ValidationException e) {
                return new ResponseEntity<>(e.getValidError(), HttpStatus.BAD_REQUEST);
            }
        } else return new ResponseEntity<>("reserve must be not null", HttpStatus.BAD_REQUEST);

    }


    @DeleteMapping(value = {"{id}"})
    public String cancelUserReserve(@PathVariable("id") long reserveId, Map<String, Object> model) {
        try {
            reserveService.delete(reserveId);
            return "redirect:/admin/reserves";
        } catch (ServiceException e) {
            model.put("error", "error");
            return "adminReserves";
        } catch (EntityNotFoundException e) {
            model.put("error", "entity.not.exist");
            return "adminReserves";
        }
    }

    @GetMapping()
    public String showAllReserves(Map<String, Object> model) {
        try {
            List<Reserve> reserves = reserveService.readAll();
            model.put("reserves", reserves);
            return "adminReserves";
        } catch (ServiceException e) {
            model.put("error", "error");
            return "adminReserves";
        }
    }
}
