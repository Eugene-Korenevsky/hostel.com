package com.spring.controllers.adminControllers;

import com.spring.model.entity.Description;
import com.spring.model.entity.Room;
import com.spring.model.entity.RoomForm;
import com.spring.model.service.DescriptionService;
import com.spring.model.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("admin/room")
public class AdminRoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private DescriptionService descriptionService;


    @GetMapping()
    public String showAdminRoomsList(Map<String, Object> model) {
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
    }

    @GetMapping(value = {"{id}"}, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<RoomForm> getRoom(@PathVariable("id") Long id) {
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
            return new ResponseEntity<RoomForm>(roomForm, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<RoomForm>(HttpStatus.BAD_REQUEST);
        }

    }


    @PutMapping(produces = {MimeTypeUtils.APPLICATION_JSON_VALUE},
            consumes = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<RoomForm> updateRoom(@RequestBody RoomForm roomForm) {
        Room room = roomService.readById(roomForm.getId(), true);
        List<Description> descriptions = descriptionService.readAll(false);
        Set<Description> roomDesc = new HashSet<>();
        System.out.println(roomForm.getPrice());
        for (Description description : descriptions) {
            for (String s : roomForm.getDescriptions()) {
                if (s.equals(description.getDescription())) {
                    roomDesc.add(description);
                    break;
                }
            }
        }
        room.setDescriptions(roomDesc);
        room.setSits(roomForm.getSits());
        room.setRoomClass(roomForm.getRoomClass());
        room.setPrice(roomForm.getPrice());
        room.setNumber(roomForm.getNumber());
        roomService.update(room);
        return new ResponseEntity<RoomForm>(HttpStatus.OK);
    }

    @PostMapping()
    public String createRoom(@ModelAttribute("room") RoomForm room) {
        Room room1 = new Room();
        room1.setNumber(room.getNumber());
        room1.setPrice(room.getPrice());
        room1.setRoomClass(room.getRoomClass());
        room1.setSits(room.getSits());
        List<Description> descriptions = descriptionService.readAll(false);
        for (String s : room.getDescriptions()) {
            for (Description description1 : descriptions) {
                if (description1.getDescription().equals(s)) {
                    room1.addDescription(description1);
                }
            }
        }
        roomService.createRoom(room1);
        return "redirect:/admin/room";
    }

    @DeleteMapping(value = {"{id}"})
    public ResponseEntity<String> deleteRoom(@PathVariable("id") long id) {
        try {
            roomService.delete(id);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }
}
