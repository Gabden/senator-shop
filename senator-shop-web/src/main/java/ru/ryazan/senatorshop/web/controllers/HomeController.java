package ru.ryazan.senatorshop.web.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ryazan.senatorshop.core.domain.Product;
import ru.ryazan.senatorshop.core.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HomeController {
    private ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping({"","/", "/index"})
    public String main(@RequestParam(name = "page", defaultValue = "0")int page, Model model){
        Pageable pageable = PageRequest.of(page, 9, Sort.by("id").descending());
        Page<Product> products = productService.findAll(pageable);
        int totalPages = products.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("products", products);
        model.addAttribute("orders", products);
        model.addAttribute("url", "/");
        return "home";
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
