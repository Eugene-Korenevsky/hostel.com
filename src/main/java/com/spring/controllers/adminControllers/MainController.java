package com.spring.controllers.adminControllers;

import com.spring.model.entity.Description;
import com.spring.model.entity.Room;
import com.spring.model.entity.RoomForm;
import com.spring.model.entity.User;
import com.spring.model.service.DescriptionService;
import com.spring.model.service.RoomService;
import com.spring.model.service.UserService;
import com.spring.model.service.exceptions.UserServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("admin")
public class MainController {
    @Autowired
    private UserService userService;

    @GetMapping(value = {"profile"})
    public String showAdminCabinet(Map<String, Object> model) {
        try {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            Authentication authentication = securityContext.getAuthentication();
            org.springframework.security.core.userdetails.User user =
                    (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
            User user1 = userService.login(user.getUsername(), user.getPassword());
            model.put("user", user1);
            return "adminProfile";
        } catch (UserServiceException e) {
            model.put("error","error");
            return "registration";
        }
    }


}
