package ru.gabdulindv.senatorshop.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/public")
public class HomeController {
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

}