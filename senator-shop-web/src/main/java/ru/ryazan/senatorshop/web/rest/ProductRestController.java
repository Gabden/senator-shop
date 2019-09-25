package ru.ryazan.senatorshop.web.rest;

import org.springframework.web.bind.annotation.*;
import ru.ryazan.senatorshop.core.domain.Product;
import ru.ryazan.senatorshop.core.service.ProductService;
import ru.ryazan.senatorshop.web.exception.MyFileNotFoundException;

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
        return getProductById(id);
    }

    private Optional<Product> getProductById(@PathVariable Long id) {
        return Optional.ofNullable(productService.findById(id)
                .filter(product -> product.getId().equals(id))
                .orElseThrow(MyFileNotFoundException::new));
    }

    @PostMapping("/admin/create")
    public void createProduct(@RequestBody Product product){
        productService.save(product);
    }

    @PutMapping("/admin/update/{id}")
    public void updateProduct(@PathVariable Long id , @RequestBody Product product){
        Optional<Product> productFromDB;
        productFromDB = Optional.of(product);
        productFromDB.ifPresent(productNew ->productNew.setId(id));
        productService.update(productFromDB);
    }

    @DeleteMapping("/admin/delete")
    public void deleteProduct(@RequestBody Product product){
        productService.delete(product);
    }

    @DeleteMapping("/admin/deleteById/{id}")
    public void deleteProductById(@PathVariable Long id){
        Optional<Product> productFromDB = getProductById(id);
        productService.deleteById(id);
    }

}
