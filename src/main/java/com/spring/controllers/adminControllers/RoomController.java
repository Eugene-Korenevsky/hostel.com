package com.spring.controllers.adminControllers;

import com.spring.model.entity.Description;
import com.spring.model.entity.Room;
import com.spring.model.entity.RoomForm;
import com.spring.model.entity.ValidError;
import com.spring.model.service.DescriptionService;
import com.spring.model.service.RoomService;
import com.spring.model.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller("AdminRoomController")
@RequestMapping("admin/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private DescriptionService descriptionService;


    @GetMapping()
    public String showAdminRoomsList(Map<String, Object> model) {
        try {
            List<Room> rooms = roomService.readAll(true);
            List<Description> descriptions = descriptionService.readAll(false);
            List<String> descNames = new ArrayList<>();
            for (Description description : descriptions) {
                descNames.add(description.getDescription());
            }
            RoomForm room = new RoomForm();
            model.put("descriptionsList", descriptions);
            model.put("room", room);
            model.put("rooms", rooms);
            return "adminRoomsList";
        } catch (RoomServiceException | DescriptionServiceException e) {
            model.put("error", "error");
            return "adminRoomList";
        }

    }

    @GetMapping(value = {"{id}"}, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getRoom(@PathVariable("id") Long id) {
        try {
            Room room = roomService.readById(id, true);
            RoomForm roomForm = new RoomForm();
            roomForm.setPrice(room.getPrice());
            roomForm.setNumber(room.getNumber());
            roomForm.setRoomClass(room.getRoomClass());
            roomForm.setSits(room.getSits());
            roomForm.setId(room.getId());
            ArrayList<String> strings = new ArrayList<>();
            for (Description description : room.getDescriptions()) {
                strings.add(description.getDescription());
            }
            roomForm.setDescriptions(strings);
            return new ResponseEntity<>(roomForm, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("resource not found", HttpStatus.NOT_FOUND);
        } catch (RoomServiceException e) {
            return new ResponseEntity<>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PutMapping(value = "{id}", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE},
            consumes = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateRoom(@RequestBody RoomForm roomForm, @PathVariable("id") Long rooId) {
        try {
            Room room = roomService.update(roomForm, rooId);
            return new ResponseEntity<>(room, HttpStatus.OK);
        } catch (RoomServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (ValidationException e) {
            ValidError validError = e.getValidError();
            return new ResponseEntity<>(validError, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public String createRoom(@ModelAttribute("room") RoomForm room, Map<String, Object> model) {
        try {
            roomService.createRoom(room);
            return "redirect:/admin/rooms";
        } catch (ValidationException e) {
            model.put("error", e.getValidError());
            return "adminRoomsList";
        } catch (RoomWithThisNumberIsExist e) {
            model.put("moreError", "room.exist");
            return "adminRoomsList";
        } catch (RoomServiceException e) {
            model.put("moreError", "error");
            return "adminRoomsList";
        }
    }

    @DeleteMapping(value = {"{id}"})
    public ResponseEntity<?> deleteRoom(@PathVariable("id") long id) {
        try {
            roomService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RoomServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("resource not found", HttpStatus.NOT_FOUND);
        }
    }
}
