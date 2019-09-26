package ru.ryazan.senatorshop.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cart")
public class CartItemController {

    @RequestMapping
    public String get (HttpServletRequest request) {
        return "redirect:/cart/" + request.getSession(true).getId();
    }

    @RequestMapping("/{cartId}")
    public String getCart(@PathVariable(value = "cartId") String id, Model model){
        model.addAttribute("cartId", id);
        return "cart";
    }
}
