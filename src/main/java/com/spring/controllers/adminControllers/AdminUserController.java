package com.spring.controllers.adminControllers;

import com.spring.model.entity.Role;
import com.spring.model.entity.User;
import com.spring.model.service.RoleService;
import com.spring.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin/users")
public class AdminUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping()
    public String showAdminUserList(Map<String, Object> model) {
        List<User> users = userService.readAll();
        List<Role> roles = roleService.readAll();
        model.put("users", users);
        model.put("roles", roles);
        return "adminUsers";
    }

    @PutMapping(produces = {MimeTypeUtils.APPLICATION_JSON_VALUE},
            consumes = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> updateUser( @RequestBody User user) {
        try {
            userService.update(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = {"{id}"}, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> showUser(@PathVariable("id") long id) {
        try {
            User user = userService.findById(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
    }
}
