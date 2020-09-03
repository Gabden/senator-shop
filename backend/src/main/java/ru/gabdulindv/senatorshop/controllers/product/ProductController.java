package ru.gabdulindv.senatorshop.controllers.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gabdulindv.senatorshop.model.product.Product;
import ru.gabdulindv.senatorshop.service.ProductDetailsService;
import ru.gabdulindv.senatorshop.service.ProductService;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;
    private ProductDetailsService productDetailsService;

    public ProductController(ProductService productService, ProductDetailsService productDetailsService) {
        this.productService = productService;
        this.productDetailsService = productDetailsService;
    }

    @RequestMapping("/{id}")
    public ResponseEntity getProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping("/all/types")
    public ResponseEntity getAllTypes() {
        Set<String> allTypes = productDetailsService.findAllTypes();
        return ResponseEntity.ok(allTypes);
    }
}
