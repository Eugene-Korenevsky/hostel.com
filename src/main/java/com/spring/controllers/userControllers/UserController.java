package com.spring.controllers.userControllers;

import com.spring.model.entity.*;
import com.spring.model.service.*;
import com.spring.model.service.exceptions.EmailIsExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.lang.reflect.Array;
import java.security.Principal;
import java.util.*;

@Controller
public class UserController {
    @Value("${access.first}")
    private String access1;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ReserveService reserveService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private DescriptionService descriptionService;

    @RequestMapping(value = {"/loginForm"}, method = RequestMethod.GET)
    public String loginUserForm(Map<String, Object> model) {
        User user = new User();
        model.put("user", user);
        return "login";
    }

    @RequestMapping(value = {"/loginError"}, method = RequestMethod.GET)
    public String loginUserFormError(Map<String, Object> model) {
        User user = new User();
        model.put("user", user);
        model.put("message","login.wrong");
        return "login";
    }

    @RequestMapping(value = {"/loginFirst"}, method = RequestMethod.GET)
    public String loginUserFirst(Map<String, Object> model) {
        User user = new User();
        model.put("user", user);
        model.put("message","login.first");
        return "login";
    }



    @GetMapping(value = {"profile"})
    public String showCabinet(Map<String, Object> model) {
        try {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            Authentication authentication = securityContext.getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            //org.springframework.security.core.userdetails.User user =
              //      (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
            User user1 = userService.login(userDetails.getUsername(), userDetails.getPassword());
            if (user1.getRole().getRole().equals(access1)) {
                model.put("user", user1);
                return "redirect:/admin/profile";
            } else {
                List<Reserve> reserves = reserveService.readAllByUserId(user1.getId());
                List<Order> orders = orderService.findByUserId(user1.getId());
                model.put("orders", orders);
                model.put("reserves", reserves);
                model.put("user", user1);
                return "profile";
            }
        } catch (Exception e) {
            return "redirect:/loginFirst";
        }

    }


    @GetMapping(value = {"registration"})
    public String showRegisterForm(Map<String, Object> model) {
        model.put("user", new User());
        return "registration";
    }

    @PostMapping(value = {"registration"})
    public String registerUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        try {
            userService.register(user);
            return "login";
        } catch (EmailIsExistException e) {
            return "registration";
        }
    }

}
