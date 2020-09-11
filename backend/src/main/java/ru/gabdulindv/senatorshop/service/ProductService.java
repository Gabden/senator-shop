package ru.gabdulindv.senatorshop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.gabdulindv.senatorshop.model.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Page<Product> findAll(Pageable pageable);

    Optional<Product> findById(Long id);

    void addProduct(Product product);

    void update(Optional<Product> product);

    Optional<Product> findProductByName(String name);

    void delete(Product product);

    void deleteById(Long id);

    Page<Product> findProductsByProductCategoryContains(String category, Pageable pageable);

    Page<Product> findProductsByProductCategoryContainsAndProductDescriptionContains(String category, String description, Pageable pageable);

    Page<Product> findProductsByProductDescriptionContains(String description, Pageable pageable);

    Page<Product> findProductsByProductDescriptionContainsOrProductNameContains(String description, String name, Pageable pageable);

    Page<Product> findProductsByProductCategoryContainsAndProductDescriptionContainsOrProductNameContains(String category, String description, String productName, Pageable pageable);

    List<Product> findAll();

    List<Product> findProductsByProductCategoryContains(String category);

    List<Product> findProductsByProductDetails_ProductTypeContains(String type);

    List<Product> findProductsByProductDetails_ProductAlcoholColorContains(String color);

    List<Product> findProductsByProductDetails_ProductManufacturerContains(String manufacturer);

    List<Product> findProductsByProductDetails_ProductCountryContains(String country);

    // FILTER requests
    Page<Product> findProductsByProductCategoryIn(List<String> categories, Pageable pageable);

    Page<Product> findProductsByProductCategoryInAndProductDetails_ProductManufacturerContains(List<String> categories, String manufacturer, Pageable pageable);

    Page<Product> findProductsByProductCategoryInAndProductDetails_ProductCountryContains(List<String> categories, String country, Pageable pageable);

    Page<Product> findProductsByProductCategoryInAndProductDetails_ProductTypeInAndProductDetails_ProductManufacturerContains(List<String> categories, List<String> types, String manufacturer, Pageable pageable);

    Page<Product> findProductsByProductCategoryInAndProductDetails_ProductTypeIn(List<String> categories, List<String> types, Pageable pageable);

    Page<Product> findProductsByProductCategoryInAndProductDetails_ProductTypeInAndProductDetails_ProductCountryContains(List<String> categories, List<String> types, String country, Pageable pageable);

    Page<Product> findProductsByProductCategoryInAndProductDetails_ProductTypeInAndProductDetails_ProductCountryContainsAndProductDetails_ProductManufacturerContains(List<String> categories, List<String> types, String country, String manufacturer, Pageable pageable);

}