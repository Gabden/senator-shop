package ru.ryazan.senatorshop.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ryazan.senatorshop.core.domain.Product;
import ru.ryazan.senatorshop.core.service.ProductService;
import ru.ryazan.senatorshop.web.exception.MyFileNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class HomeController {
    private ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping({"","/", "/index"})
    public String main(Model model){
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

}