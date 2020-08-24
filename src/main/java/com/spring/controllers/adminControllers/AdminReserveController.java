package com.spring.controllers.adminControllers;

import com.spring.model.entity.Reserve;
import com.spring.model.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin/reserve")
public class AdminReserveController {
    @Autowired
    private ReserveService reserveService;

    @PostMapping(consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reserve> createReserve(@RequestBody Reserve reserve){
        try {
            reserveService.create(reserve.getId());
            System.out.println(reserve.getUser().getName());
            System.out.println(reserve.getUser().getSurname());
            return new ResponseEntity<>(reserve,HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping(value = {"{id}"})
    public String cancelUserReserve(@PathVariable("id") long reserveId) {
        reserveService.delete(reserveId);
        return "redirect:/admin/reserve";
    }

    @GetMapping()
    public String showAllReserves(Map<String, Object> model){
        List<Reserve> reserves = reserveService.readAll();
        model.put("reserves",reserves);
        return "adminReserves";
    }
}
