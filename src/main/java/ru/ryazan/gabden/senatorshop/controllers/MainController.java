package ru.ryazan.gabden.senatorshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ryazan.gabden.senatorshop.domain.User;
import ru.ryazan.gabden.senatorshop.service.UserService;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String startPage(Model model){
        List<User> allUsers = userService.findAll();
        model.addAttribute("users", allUsers);
        return "index";
    }
}
