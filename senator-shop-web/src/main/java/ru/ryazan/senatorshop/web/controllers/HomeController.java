package ru.ryazan.senatorshop.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ryazan.senatorshop.core.domain.Product;
import ru.ryazan.senatorshop.core.service.ProductService;

import java.util.List;

@Controller
public class HomeController {
    private ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping({"","/", "/index"})
    public String main(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "home";
    }

    @RequestMapping("/upload")
    public String upload(Model model){
        model.addAttribute("products", productService.findAll());
        return "uploadTest";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    @RequestMapping("/reserve")
    public String reserve(){
        return "reserve";
    }

    @RequestMapping("/rules")
    public String rules(){
        return "rules";
    }

}
