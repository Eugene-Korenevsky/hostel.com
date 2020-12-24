package com.spring.controllers.userControllers;

import com.spring.model.entity.Reserve;
import com.spring.model.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("reserves")// add user in path!!!
public class ReserveController {
    @Autowired
    private ReserveService reserveService;

    @DeleteMapping(value = {"{id}"})
    public ResponseEntity<Reserve> deleteReserve(@PathVariable("id") long id) {
        try {
            reserveService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
