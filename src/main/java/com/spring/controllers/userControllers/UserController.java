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

    /*@RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String loginUser(@Valid User user, BindingResult bindingResult,
                            HttpSession session,
                            Map<String, Object> model) {

        if (bindingResult.hasErrors()) {
            return "loginForm";
        }
        User user1 = userService.login(user.getEmail(), user.getPassword());
        if (user1.getRole() != null) {
            session.setAttribute("user", user1);
            model.put("message", "login.welcome");
            return "user";
        } else {
            model.put("message", "login.wrong");
            return "loginForm";
        }
    }*/

    @RequestMapping(value = {"profile"}, method = RequestMethod.GET)
    public String showCabinet(Map<String, Object> model) {
        try {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            Authentication authentication = securityContext.getAuthentication();
            org.springframework.security.core.userdetails.User user =
                    (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
            User user1 = userService.login(user.getUsername(), user.getPassword());
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
            return "login";
        }

    }



   /* @RequestMapping(value = {"changeRole"}, method = RequestMethod.POST)
    public String changeUserRole(HttpSession session, @RequestParam(value = "roleId") long roleId,
                                 @RequestParam(value = "userId") long userId) {
        User user = (User) session.getAttribute("user");
        if (user.getRole().getRole().equals(access1)) {
            userService.changeUserRole(userId, roleId);
            return "redirect:/usersList";
        } else return "loginForm";
    }*/

    @RequestMapping(value = {"registration"}, method = RequestMethod.GET)
    public String showRegisterForm(Map<String, Object> model) {
        User user = new User();
        model.put("user", user);
        return "registration";
    }

    @RequestMapping(value = {"/registration"}, method = RequestMethod.POST)
    public String registerUser(@Valid User user, BindingResult bindingResult,
                               Map<String, Object> model) {

        if (bindingResult.hasErrors()) {
            System.out.println("error");
            return "registration";
        }
        try {
            userService.register(user);
            return "login";
        } catch (EmailIsExistException e) {
            return "index";
        }
    }

}