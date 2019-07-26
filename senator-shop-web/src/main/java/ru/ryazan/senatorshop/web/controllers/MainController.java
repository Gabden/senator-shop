package ru.ryazan.senatorshop.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ryazan.senatorshop.core.domain.User;
import ru.ryazan.senatorshop.core.service.UserService;


import java.util.List;

@Controller
public class MainController {

    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("")
    public String startPage(Model model){
        List<User> allUsers = userService.findAll();
        System.out.println(allUsers.size());
        model.addAttribute("users", allUsers);
        return "index";
    }
}
