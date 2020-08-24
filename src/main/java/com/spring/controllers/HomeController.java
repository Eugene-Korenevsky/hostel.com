package com.spring.controllers;

import com.spring.model.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class HomeController {
    @GetMapping(value = {"/", "/home"})
    public String showHomePage() {
        return "index";
    }

}
