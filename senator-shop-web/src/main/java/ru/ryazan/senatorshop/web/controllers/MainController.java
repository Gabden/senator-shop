package ru.ryazan.senatorshop.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ryazan.senatorshop.core.service.ProductService;

@Controller
public class MainController {
    private ProductService productService;

    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping({"","/", "/index"})
    public String main(Model model){
        model.addAttribute("text", "text from thymeleaf");
        return "home";
    }

    @RequestMapping("/productList")
    public String productList(Model model){
        model.addAttribute("products", productService.findAll());
        return "product-list";
    }
}
