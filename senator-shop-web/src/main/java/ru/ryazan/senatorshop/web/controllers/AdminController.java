package ru.ryazan.senatorshop.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ryazan.senatorshop.core.domain.Product;
import ru.ryazan.senatorshop.core.domain.ProductImage;
import ru.ryazan.senatorshop.core.service.ProductImageService;
import ru.ryazan.senatorshop.core.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private ProductService productService;

    private ProductImageService DBFileStorageService;

    public AdminController(ProductService productService, ProductImageService DBFileStorageService) {
        this.productService = productService;
        this.DBFileStorageService = DBFileStorageService;
    }
    @RequestMapping("")
    public String admin(Model model){
        Optional<Product> product = productService.findById(44L);
        product.ifPresent(p -> System.out.println(p.getProductImageSet().isEmpty()));
        model.addAttribute("text", "text from thymeleaf");
        List<ProductImage> images =new ArrayList<>();
        images.addAll(product.get().getProductImageSet());
        String imge =  Base64.getEncoder().encodeToString(images.get(0).getFileData());
        model.addAttribute("image", imge);
        return "admin";
    }
    @RequestMapping("/productInventory")
    public String productInventory(Model model){
        List<Product> allProducts = productService.findAll();

        model.addAttribute("products", allProducts);

        return "product-inventory";
    }

    @RequestMapping("/productInventory/addProduct")
    public String adminAdd(Model model){
        Product product = new Product();
        ProductImage image = new ProductImage();
        model.addAttribute("product", product);
        model.addAttribute("image", image);
        return "addProduct";
    }

    @RequestMapping(value = "/productInventory/addProduct", method = RequestMethod.POST)
    public String productInventoryAdd(@ModelAttribute("product") Product product, @RequestParam("file") MultipartFile file,HttpServletRequest request){
        productService.save(product);
        ProductImage dbFile = DBFileStorageService.storeFile(file, product);

        return "redirect:/admin/productInventory";
    }

    @RequestMapping(value = "/productInventory/updateProduct/{id}")
    public String updateProduct(@PathVariable Long id, Model model){
        Optional<Product> product = productService.findById(id);
        product.ifPresent(model::addAttribute);
        return "edit-product";
    }

    @RequestMapping(value = "/productInventory/updateProduct")
    public String update(@ModelAttribute("product")Product product, Model model, HttpServletRequest request) {

        return "/admin/productInventory";
    }

    @RequestMapping(value = "/productInventory/deleteProduct/{id}")
    public String productInventoryDelete(@PathVariable Long id){
        productService.deleteById(id);
        return "redirect:/admin/productInventory";
    }


}
