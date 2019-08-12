package ru.ryazan.senatorshop.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.ryazan.senatorshop.core.domain.Product;
import ru.ryazan.senatorshop.core.service.ProductService;
import ru.ryazan.senatorshop.web.exceptions.NotFoundException;

import java.util.Optional;

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

    @RequestMapping("/admin")
    public String admin(Model model){
        model.addAttribute("text", "text from thymeleaf");
        return "admin";
    }
    @RequestMapping("/admin/productInventory")
    public String productInventory(Model model){
        model.addAttribute("products", productService.findAll());
        return "product-inventory";
    }

    @RequestMapping("/admin/productInventory/addProduct")
    public String adminAdd(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "addProduct";
    }

    @RequestMapping(value = "/admin/productInventory/addProduct", method = RequestMethod.POST)
    public String productInventoryAdd(@ModelAttribute("product") Product product){
        productService.save(product);
        System.out.println(product.toString());
        return "redirect:/admin/productInventory";
    }

    @RequestMapping("/productList")
    public String productList(Model model){
        model.addAttribute("products", productService.findAll());
        return "product-list";
    }

    @RequestMapping("/productList/product/{id}")
    public String product(@PathVariable Long id, Model model){
        Optional<Product> productFromDB = Optional.ofNullable(productService.findById(id)
                .filter(product -> product.getId().equals(id))
                .orElseThrow(NotFoundException::new));
        model.addAttribute("product", productFromDB.get());
        return "product";
    }
}
