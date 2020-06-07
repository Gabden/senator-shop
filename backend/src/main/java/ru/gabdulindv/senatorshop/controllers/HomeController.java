package ru.gabdulindv.senatorshop.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.gabdulindv.senatorshop.model.User;
import ru.gabdulindv.senatorshop.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/public")
public class HomeController {
    private UserRepository repository;

    public HomeController(UserRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/hello")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> hello() {
        HashMap<String, String> map = new HashMap<>();
        map.put("all", "Available for all users");
        return map;
    }

    @RequestMapping("/manager/hello")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> manager() {
        HashMap<String, String> map = new HashMap<>();
        map.put("all", "Available for manager users");
        return map;
    }

    @RequestMapping("/admin/hello")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> admin() {
        HashMap<String, String> map = new HashMap<>();
        map.put("all", "Available for admin users");
        return map;
    }

    @RequestMapping("/user")
    public User getUser(Authentication auth, HttpServletRequest request) {
        System.out.println("--------------------");
        System.out.println(request.getHeader("Authorization"));
        User user = repository.findByUsername(auth.getName());
        user.setToken(request.getHeader("Authorization"));
        return user;
    }
}