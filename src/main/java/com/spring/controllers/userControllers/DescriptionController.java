package com.spring.controllers.userControllers;

import com.spring.model.entity.User;
import com.spring.model.service.DescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class DescriptionController {
    @Value("${access.first}")
    private String access1;
    @Autowired
    private DescriptionService descriptionService;

    @RequestMapping(value = {"deleteDescription"}, method = RequestMethod.POST)
    public String deleteDescription(HttpSession session, @RequestParam(value = "descriptionId") long descriptionId) {
        User user = (User) session.getAttribute("user");
        if (user.getRole().getRole().equals(access1)) {
            descriptionService.delete(descriptionId);
            return "redirect:/roomsList";
        } else return "loginForm";
    }

    @RequestMapping(value = "createDescription", method = RequestMethod.POST)
    public String createDescription(HttpSession session, @RequestParam(value = "description") String descriptionName) {
        User user = (User) session.getAttribute("user");
        if (user.getRole().getRole().equals(access1)) {
            descriptionService.create(descriptionName);
            return "redirect:/roomsList";
        } else return "loginForm";
    }
}
