package ru.ryazan.senatorshop.web.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String productList(@RequestParam(name = "page", defaultValue = "0")int page, Model model){
        Pageable pageable = PageRequest.of(page, 10, Sort.by("id").descending());
        model.addAttribute("products", productService.findAll(pageable));
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
    public Product productApi(@PathVariable Long id) {
        Optional<Product> productFromDB = Optional.ofNullable(productService.findById(id)
                .filter(product -> product.getId().equals(id))
                .orElseThrow(MyFileNotFoundException::new));
        return productFromDB.get();
    }

    @RequestMapping("/productList/product/image/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> productImage(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            byte[] imageByteArray = product.get().getByteArrayImage();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setCacheControl(CacheControl.noCache().getHeaderValue());
            httpHeaders.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imageByteArray, httpHeaders, HttpStatus.OK);
        } else {
            throw new MyFileNotFoundException("Image with id" + id + " doesn`t exist");
        }

    }
}
