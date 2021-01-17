package com.spring.controllers.adminControllers;

import com.spring.model.entity.Role;
import com.spring.model.entity.User;
import com.spring.model.service.RoleService;
import com.spring.model.service.UserService;
import com.spring.model.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller("AdminUserController")
@RequestMapping("admin/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping()
    public String showAdminUserList(Map<String, Object> model) {
        try {
            List<User> users = userService.readAll();
            List<Role> roles = roleService.readAll();
            model.put("users", users);
            model.put("roles", roles);
            return "adminUsers";
        } catch (ServiceException e) {
            model.put("error", "error");
            return "adminUsers";
        }
    }

    @PutMapping(value = {"{id}"}, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE},
            consumes = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable("id") long id) {
        if (user != null && user.getId() == id) {
            try {
                userService.update(user,id);
                return new ResponseEntity<>(user, HttpStatus.OK);
            } catch (EntityNotFoundException e) {
                return new ResponseEntity<>("resource not found", HttpStatus.NOT_FOUND);
            } catch (ValidationException e) {
                return new ResponseEntity<>(e.getValidError(), HttpStatus.BAD_REQUEST);
            } catch (ServiceException e) {
                return new ResponseEntity<>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else return new ResponseEntity<>("user mustn't be null or incorrect resource id in the request path",
                HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = {"{id}"}, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> showUser(@PathVariable("id") long id) {
        try {
            User user = userService.findById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("resource not found", HttpStatus.NOT_FOUND);
        } catch (ServiceException e) {
            return new ResponseEntity<>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
