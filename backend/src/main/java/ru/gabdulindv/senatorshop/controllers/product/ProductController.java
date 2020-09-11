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
import java.util.stream.Collectors;

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
        Set<String> allTypesSorted = allTypes.parallelStream().map(type -> {
            if (type != null && type.length() > 0) {
                String typeTrimmed = type.trim().toLowerCase();
                return typeTrimmed.substring(0, 1).toUpperCase() + typeTrimmed.substring(1);
            }
            return "Ром";
        }).collect(Collectors.toSet());
        return ResponseEntity.ok(allTypesSorted.stream().sorted());
    }

    @RequestMapping("/all/manufacturers")
    public ResponseEntity getAllManufacturers() {
        Set<String> manufacturers = productDetailsService.findAllManufacturers();
        Set<String> sortedMan = manufacturers.parallelStream().map(manufacturer -> {
            if (manufacturer != null && manufacturer.length() > 0) {
                String manufacturerTrimmed = manufacturer.trim().toLowerCase();
                return manufacturerTrimmed.substring(0, 1).toUpperCase() + manufacturerTrimmed.substring(1);
            }
            return "Delamain";
        }).collect(Collectors.toSet());
        return ResponseEntity.ok(sortedMan.stream().sorted());
    }

    @RequestMapping("/all/countries")
    public ResponseEntity getAllCountries() {
        Set<String> countries = productDetailsService.findAllCountries();
        Set<String> countriesSorted = countries.parallelStream().map(country -> {
            if (country != null && country.length() > 0) {
                String countryTrimmed = country.trim().toLowerCase();
                return countryTrimmed.substring(0, 1).toUpperCase() + countryTrimmed.substring(1);
            }
            return "Франция";
        }).collect(Collectors.toSet());
        return ResponseEntity.ok(countriesSorted.stream().sorted());
    }
}
