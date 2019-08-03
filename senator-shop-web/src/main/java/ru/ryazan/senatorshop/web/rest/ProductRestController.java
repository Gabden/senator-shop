package ru.ryazan.senatorshop.web.rest;

import org.springframework.web.bind.annotation.*;
import ru.ryazan.senatorshop.core.domain.Product;
import ru.ryazan.senatorshop.core.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class ProductRestController {

    private ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAll")
    public List<Product> findAll (){
        return productService.findAll();
    }

    @GetMapping("/get/{id}")
    public Optional<Product> findById (@PathVariable Long id){
        return productService.findById(id);
    }

    @PostMapping("/save")
    public void saveProduct(Product product){
        productService.save(product);
    }

    @PutMapping("/update")
    public void updateProduct(Product product){
        productService.update(product);
    }

    @DeleteMapping("/delete")
    public void deleteProduct(Product product){
        productService.delete(product);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteProductById(@PathVariable Long id){
        productService.deleteById(id);
    }

}
