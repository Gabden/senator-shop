package ru.gabdulindv.senatorshop.controllers.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gabdulindv.senatorshop.model.product.Product;
import ru.gabdulindv.senatorshop.service.ProductService;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminProductController {
    private ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/products")
    public ResponseEntity getProducts(@RequestParam(name = "page", defaultValue = "0") int page) {
        if (page < 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, 10, Sort.by("productId").descending());
        Page<Product> allProducts = productService.findAll(pageable);

        return ResponseEntity.ok(allProducts);
    }

    @RequestMapping("/product/{id}")
    public ResponseEntity getProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        }
        return ResponseEntity.notFound().build();
    }
}
