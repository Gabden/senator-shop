package ru.ryazan.senatorshop.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ryazan.senatorshop.core.domain.Product;
import ru.ryazan.senatorshop.core.service.ProductService;
import ru.ryazan.senatorshop.web.exception.MyFileNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/productList")
    public String productList(Model model){
        model.addAttribute("products", productService.findAll());
        return "product-list";
    }

    @RequestMapping("/productList/product/{id}")
    public String product(@PathVariable Long id, Model model, HttpServletRequest request){
        Optional<Product> productFromDB = Optional.ofNullable(productService.findById(id)
                .filter(product -> product.getId().equals(id))
                .orElseThrow(MyFileNotFoundException::new));
        model.addAttribute("product", productFromDB.get());
        return "product";
    }

    @RequestMapping("/productList/product/api/{id}")
    @ResponseBody
    public   Product productApi(@PathVariable Long id){
        Optional<Product> productFromDB = Optional.ofNullable(productService.findById(id)
                .filter(product -> product.getId().equals(id))
                .orElseThrow(MyFileNotFoundException::new));
        return productFromDB.get();
    }
}
