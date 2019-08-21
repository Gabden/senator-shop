package ru.ryazan.senatorshop.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.ryazan.senatorshop.core.domain.Product;
import ru.ryazan.senatorshop.core.domain.ProductImage;
import ru.ryazan.senatorshop.core.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }
    @RequestMapping("")
    public String admin(Model model){
        Optional<Product> product = productService.findById(44L);
        product.ifPresent(p -> System.out.println(p.getProductImageSet().isEmpty()));
        model.addAttribute("text", "text from thymeleaf");
        List<ProductImage> images =new ArrayList<>();
        images.addAll(product.get().getProductImageSet());

        String imge =  Base64.getEncoder().encodeToString(images.get(0).getFileData());
        System.out.println(imge);
        model.addAttribute("image", imge);
        return "admin";
    }
    @RequestMapping("/productInventory")
    public String productInventory(Model model){
        model.addAttribute("products", productService.findAll());
        return "product-inventory";
    }

    @RequestMapping("/productInventory/addProduct")
    public String adminAdd(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "addProduct";
    }

    @RequestMapping(value = "/productInventory/addProduct", method = RequestMethod.POST)
    public String productInventoryAdd(@ModelAttribute("product") Product product, HttpServletRequest request){
        productService.save(product);

        return "redirect:/productInventory";
    }

    @RequestMapping(value = "/productInventory/updateProduct/{id}")
    public String updateProduct(@PathVariable Long id, Model model){
        Optional<Product> product = productService.findById(id);
        product.ifPresent(model::addAttribute);
        return "edit-product";
    }

    @RequestMapping(value = "/productInventory/updateProduct")
    public String update(@ModelAttribute("product")Product product, Model model, HttpServletRequest request) {

        return "productInventory";
    }

    @RequestMapping(value = "/productInventory/deleteProduct/{id}")
    public String productInventoryDelete(@PathVariable Long id){
        productService.deleteById(id);
        return "redirect:/productInventory";
    }


}
