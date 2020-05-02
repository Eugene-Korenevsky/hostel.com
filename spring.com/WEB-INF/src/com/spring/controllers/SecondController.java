package com.spring.controllers;

import com.spring.model.entity.Role;
import com.spring.model.entitymanager.EntityManagerFactory;
import com.spring.model.service.MainServiceFactory;
import com.spring.model.service.RoleService;
import com.spring.model.service.ServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.persistence.EntityManager;
import java.util.Map;

@Controller
@SessionAttributes("person")
public class SecondController {
    @RequestMapping(value = {"/rooms"}, method = RequestMethod.GET)
    public String showHomePage(Map<String, Object> model, @ModelAttribute("person") String person) {
        Role role = new Role();
        long id = 1;
        MainServiceFactory mainServiceFactory = new ServiceFactory();
        RoleService roleService = mainServiceFactory.getRoleService();
        role = roleService.findById(id);
        mainServiceFactory.close();
        model.put("room", role.getRole());
        String person1 = person + "fromSecondController";
        model.put("person1",person1);
        return "home1";
    }
    @ModelAttribute("person")
    public String getPerson(){
        return new String("empty");
    }
}
