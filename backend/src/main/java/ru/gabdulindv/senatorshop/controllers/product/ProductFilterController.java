package ru.gabdulindv.senatorshop.controllers.product;

import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gabdulindv.senatorshop.model.product.Filter;
import ru.gabdulindv.senatorshop.model.product.Product;
import ru.gabdulindv.senatorshop.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductFilterController {
    private ProductService productService;

    public ProductFilterController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public ResponseEntity filterProductsCategory(@RequestParam(value = "page", defaultValue = "0") int page, @RequestBody Filter filter) {
        if (page < 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, 8, Sort.by("productId").descending());
        Page<Product> products;

        boolean hasCategory = filter.getSelectedCategories() != null && filter.getSelectedCategories().size() > 0;
        boolean hasType = filter.getSelectedTypes() != null && filter.getSelectedTypes().size() > 0;
        boolean hasCountry = filter.getSelectedCountry() != null && filter.getSelectedCountry().length() > 0;
        boolean hasManufacturer = filter.getSelectedManufacturer() != null && filter.getSelectedManufacturer().length() > 0;

        List<Product> filterProducts = new ArrayList<>();
        if (hasCategory && !hasType && !hasCountry && !hasManufacturer) {
            for (String category : filter.getSelectedCategories()) {
                List<Product> foundedProducts = productService.findProductsByProductCategoryContains(category);
                filterProducts.addAll(foundedProducts);
            }
        }

        if (hasCategory && hasType && !hasCountry && !hasManufacturer) {
            for (String category : filter.getSelectedCategories()) {
                List<Product> foundedProducts = productService.findProductsByProductCategoryContains(category);
                filterProducts.addAll(foundedProducts);
            }

            for (String type : filter.getSelectedTypes()) {
                List<Product> foundedProducts = productService.findProductsByProductDetails_ProductTypeContains(type);
                filterProducts.addAll(foundedProducts);
            }
        }

        if (hasCategory && !hasType && hasCountry && !hasManufacturer) {
            for (String category : filter.getSelectedCategories()) {
                List<Product> foundedProducts = productService.findProductsByProductCategoryContains(category);
                filterProducts.addAll(foundedProducts);
            }
            List<Product> countryProducts = productService.findProductsByProductDetails_ProductCountryContains(filter.getSelectedCountry());
            filterProducts.addAll(countryProducts);
        }

        if (hasCategory && !hasType && !hasCountry && hasManufacturer) {
            for (String category : filter.getSelectedCategories()) {
                List<Product> foundedProducts = productService.findProductsByProductCategoryContains(category);
                filterProducts.addAll(foundedProducts);
            }
            List<Product> manufacturerProducts = productService.findProductsByProductDetails_ProductManufacturerContains(filter.getSelectedManufacturer());
            filterProducts.addAll(manufacturerProducts);
        }

        if (!hasCategory && hasType && hasCountry && !hasManufacturer) {
            for (String type : filter.getSelectedTypes()) {
                List<Product> foundedProducts = productService.findProductsByProductDetails_ProductTypeContains(type);
                filterProducts.addAll(foundedProducts);
            }
            List<Product> countryProducts = productService.findProductsByProductDetails_ProductCountryContains(filter.getSelectedCountry());
            filterProducts.addAll(countryProducts);
        }

        if (!hasCategory && hasType && !hasCountry && !hasManufacturer) {
            for (String type : filter.getSelectedTypes()) {
                List<Product> foundedProducts = productService.findProductsByProductDetails_ProductTypeContains(type);
                filterProducts.addAll(foundedProducts);
            }
        }

        if (!hasCategory && hasType && !hasCountry && hasManufacturer) {
            for (String type : filter.getSelectedTypes()) {
                List<Product> foundedProducts = productService.findProductsByProductDetails_ProductTypeContains(type);
                filterProducts.addAll(foundedProducts);
            }
            List<Product> manufacturerProducts = productService.findProductsByProductDetails_ProductManufacturerContains(filter.getSelectedManufacturer());
            filterProducts.addAll(manufacturerProducts);
        }

        if (!hasCategory && !hasType && hasCountry && !hasManufacturer) {
            List<Product> countryProducts = productService.findProductsByProductDetails_ProductCountryContains(filter.getSelectedCountry());
            filterProducts.addAll(countryProducts);
        }

        if (!hasCategory && !hasType && hasCountry && hasManufacturer) {
            List<Product> countryProducts = productService.findProductsByProductDetails_ProductCountryContains(filter.getSelectedCountry());
            List<Product> manufacturerProducts = productService.findProductsByProductDetails_ProductManufacturerContains(filter.getSelectedManufacturer());
            filterProducts.addAll(countryProducts);
            filterProducts.addAll(manufacturerProducts);
        }

        if (!hasCategory && !hasType && !hasCountry && hasManufacturer) {
            List<Product> manufacturerProducts = productService.findProductsByProductDetails_ProductManufacturerContains(filter.getSelectedManufacturer());
            filterProducts.addAll(manufacturerProducts);
        }

        if (hasCategory && hasType && hasCountry && !hasManufacturer) {
            for (String category : filter.getSelectedCategories()) {
                List<Product> foundedProducts = productService.findProductsByProductCategoryContains(category);
                filterProducts.addAll(foundedProducts);
            }

            for (String type : filter.getSelectedTypes()) {
                List<Product> foundedProducts = productService.findProductsByProductDetails_ProductTypeContains(type);
                filterProducts.addAll(foundedProducts);
            }

            List<Product> countryProducts = productService.findProductsByProductDetails_ProductCountryContains(filter.getSelectedCountry());
            filterProducts.addAll(countryProducts);
        }

        if (hasCategory && hasType && !hasCountry && hasManufacturer) {
            for (String category : filter.getSelectedCategories()) {
                List<Product> foundedProducts = productService.findProductsByProductCategoryContains(category);
                filterProducts.addAll(foundedProducts);
            }

            for (String type : filter.getSelectedTypes()) {
                List<Product> foundedProducts = productService.findProductsByProductDetails_ProductTypeContains(type);
                filterProducts.addAll(foundedProducts);
            }

            List<Product> manufacturerProducts = productService.findProductsByProductDetails_ProductManufacturerContains(filter.getSelectedManufacturer());
            filterProducts.addAll(manufacturerProducts);
        }

        if (hasCategory && !hasType && hasCountry && hasManufacturer) {
            for (String category : filter.getSelectedCategories()) {
                List<Product> foundedProducts = productService.findProductsByProductCategoryContains(category);
                filterProducts.addAll(foundedProducts);
            }

            List<Product> countryProducts = productService.findProductsByProductDetails_ProductCountryContains(filter.getSelectedCountry());
            filterProducts.addAll(countryProducts);

            List<Product> manufacturerProducts = productService.findProductsByProductDetails_ProductManufacturerContains(filter.getSelectedManufacturer());
            filterProducts.addAll(manufacturerProducts);
        }

        if (!hasCategory && hasType && hasCountry && hasManufacturer) {
            for (String type : filter.getSelectedTypes()) {
                List<Product> foundedProducts = productService.findProductsByProductDetails_ProductTypeContains(type);
                filterProducts.addAll(foundedProducts);
            }

            List<Product> countryProducts = productService.findProductsByProductDetails_ProductCountryContains(filter.getSelectedCountry());
            filterProducts.addAll(countryProducts);

            List<Product> manufacturerProducts = productService.findProductsByProductDetails_ProductManufacturerContains(filter.getSelectedManufacturer());
            filterProducts.addAll(manufacturerProducts);
        }

        if (hasCategory && hasType && hasCountry && hasManufacturer) {
            for (String category : filter.getSelectedCategories()) {
                List<Product> foundedProducts = productService.findProductsByProductCategoryContains(category);
                filterProducts.addAll(foundedProducts);
            }

            for (String type : filter.getSelectedTypes()) {
                List<Product> foundedProducts = productService.findProductsByProductDetails_ProductTypeContains(type);
                filterProducts.addAll(foundedProducts);
            }

            List<Product> countryProducts = productService.findProductsByProductDetails_ProductCountryContains(filter.getSelectedCountry());
            filterProducts.addAll(countryProducts);

            List<Product> manufacturerProducts = productService.findProductsByProductDetails_ProductManufacturerContains(filter.getSelectedManufacturer());
            filterProducts.addAll(manufacturerProducts);
        }


        int start = (int) pageable.getOffset();
        int end = (int) (start + pageable.getPageSize()) > filterProducts.size() ? filterProducts.size() : (start + pageable.getPageSize());
        products = new PageImpl<>(filterProducts.subList(start, end), pageable, filterProducts.size());


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
