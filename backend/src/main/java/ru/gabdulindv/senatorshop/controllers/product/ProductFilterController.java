package ru.gabdulindv.senatorshop.controllers.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gabdulindv.senatorshop.model.product.Filter;
import ru.gabdulindv.senatorshop.model.product.Product;
import ru.gabdulindv.senatorshop.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductFilterController {
    private ProductService productService;

    public ProductFilterController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/filter/category", method = RequestMethod.POST)
    public ResponseEntity filterProductsCategory(@RequestParam(value = "page", defaultValue = "0") int page, @RequestBody Filter filter) {
        Pageable pageable = PageRequest.of(page, 8, Sort.by("productId").descending());
        Page<Product> products = productService.findProductsByProductCategoryIn(filter.getSelectedCategories(), pageable);
        return ResponseEntity.ok(products);
    }

    @RequestMapping(value = "/filter/category/type", method = RequestMethod.POST)
    public ResponseEntity filterProductsCategoryType(@RequestParam(value = "page", defaultValue = "0") int page, @RequestBody Filter filter) {
        Pageable pageable = PageRequest.of(page, 8, Sort.by("productId").descending());
        Page<Product> products = productService.findProductsByProductCategoryInAndProductDetails_ProductTypeIn(filter.getSelectedCategories(), filter.getSelectedTypes(), pageable);
        return ResponseEntity.ok(products);
    }

    @RequestMapping(value = "/filter/category/country", method = RequestMethod.POST)
    public ResponseEntity filterProductsCategoryCountry(@RequestParam(value = "page", defaultValue = "0") int page, @RequestBody Filter filter) {
        Pageable pageable = PageRequest.of(page, 8, Sort.by("productId").descending());
        Page<Product> products = productService.findProductsByProductCategoryInAndProductDetails_ProductCountryContains(filter.getSelectedCategories(), filter.getSelectedCountry(), pageable);
        return ResponseEntity.ok(products);
    }

    @RequestMapping(value = "/filter/category/manufacturer", method = RequestMethod.POST)
    public ResponseEntity filterProductsCategoryManufacturer(@RequestParam(value = "page", defaultValue = "0") int page, @RequestBody Filter filter) {
        Pageable pageable = PageRequest.of(page, 8, Sort.by("productId").descending());
        Page<Product> products = productService.findProductsByProductCategoryInAndProductDetails_ProductManufacturerContains(filter.getSelectedCategories(), filter.getSelectedManufacturer(), pageable);
        return ResponseEntity.ok(products);
    }

    @RequestMapping(value = "/filter/category/type/country", method = RequestMethod.POST)
    public ResponseEntity filterProductsCategoryTypeCountry(@RequestParam(value = "page", defaultValue = "0") int page, @RequestBody Filter filter) {
        Pageable pageable = PageRequest.of(page, 8, Sort.by("productId").descending());
        Page<Product> products = productService.findProductsByProductCategoryInAndProductDetails_ProductTypeInAndProductDetails_ProductCountryContains(filter.getSelectedCategories(), filter.getSelectedTypes(), filter.getSelectedCountry(), pageable);
        return ResponseEntity.ok(products);
    }

    @RequestMapping(value = "/filter/category/type/manufacturer", method = RequestMethod.POST)
    public ResponseEntity filterProductsCategoryTypeManufacturer(@RequestParam(value = "page", defaultValue = "0") int page, @RequestBody Filter filter) {
        Pageable pageable = PageRequest.of(page, 8, Sort.by("productId").descending());
        Page<Product> products = productService.findProductsByProductCategoryInAndProductDetails_ProductTypeInAndProductDetails_ProductManufacturerContains(filter.getSelectedCategories(), filter.getSelectedTypes(), filter.getSelectedManufacturer(), pageable);
        return ResponseEntity.ok(products);
    }

    @RequestMapping(value = "/filter/category/type/manufacturer/country", method = RequestMethod.POST)
    public ResponseEntity filterProductsCategoryTypeCountryManufacturer(@RequestParam(value = "page", defaultValue = "0") int page, @RequestBody Filter filter) {
        Pageable pageable = PageRequest.of(page, 8, Sort.by("productId").descending());
        Page<Product> products = productService.findProductsByProductCategoryInAndProductDetails_ProductTypeInAndProductDetails_ProductCountryContainsAndProductDetails_ProductManufacturerContains(filter.getSelectedCategories(), filter.getSelectedTypes(), filter.getSelectedCountry(), filter.getSelectedManufacturer(), pageable);
        return ResponseEntity.ok(products);
    }
}
