package com.spring.controllers;

import com.spring.model.entity.Role;
import com.spring.model.entity.User;
import com.spring.model.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Value("${access.first}")
    private String access1;


    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String loginUserForm(Map<String, Object> model) {
        User user = new User();
        model.put("user", user);
        return "loginForm";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
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
    }

    @RequestMapping(value = {"cabinet"}, method = RequestMethod.GET)
    public String showCabinet(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return "cabinet";
        } else return "loginForm";
    }

    @RequestMapping(value = {"usersList"}, method = RequestMethod.GET)
    public String showUserList(HttpSession session, Map<String, Object> model) {
        User user = (User) session.getAttribute("user");
        if (user.getRole().getRole().equals(access1)) {
            List<Role> roles = roleService.readAll();
            List<User> users = userService.readAll();
            model.put("users", users);
            model.put("roles", roles);
            return "users";
        }
        return "loginForm";
    }

    @RequestMapping(value = {"changeRole"}, method = RequestMethod.POST)
    public String changeUserRole(HttpSession session, @RequestParam(value = "roleId") long roleId,
                                 @RequestParam(value = "userId") long userId) {
        User user = (User) session.getAttribute("user");
        if (user.getRole().getRole().equals(access1)) {
            userService.changeUserRole(userId, roleId);
            return "redirect:/usersList";
        } else return "loginForm";
    }

    @RequestMapping(value = {"registerForm"}, method = RequestMethod.GET)
    public String showRegisterForm() {
        return "registerForm";
    }

    @RequestMapping(value = {"register"}, method = RequestMethod.POST)
    public String register(@RequestParam(value = "name", defaultValue = "unknown") String name,
                           @RequestParam(value = "surname", defaultValue = "unknown") String surname,
                           @RequestParam(value = "email") String email, @RequestParam(value = "password") String password,
                           Map<String, Object> model, HttpSession session) {
        if (email.length() >= 2 && password.length() >= 2) {
            User user = userService.register(email, password, name, surname);
            if (user.getRole() != null) {
                session.setAttribute("user", user);
                return "cabinet";
            } else {
                model.put("message", "wrong.email");
                return "registerForm";
            }
        } else {
            model.put("message", "put.email.or.password");
            return "registerForm";
        }
    }
}
