package com.spring.controllers.userControllers;

import com.spring.model.entity.Reserve;
import com.spring.model.service.ReserveService;
import com.spring.model.service.exceptions.EntityNotFoundException;
import com.spring.model.service.exceptions.ReserveServiceException;
import com.spring.model.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("reserves")
public class ReserveController {
    @Autowired
    private ReserveService reserveService;

    @DeleteMapping(value = {"{id}"})
    public ResponseEntity<?> deleteReserve(@PathVariable("id") long id) {
        try {
            reserveService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("resource not found", HttpStatus.NOT_FOUND);
        } catch (ServiceException e) {
            return new ResponseEntity<>("something wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
