package ru.gabdulindv.senatorshop.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.gabdulindv.senatorshop.model.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Page<Product> findProductsByProductCategoryContainsAndProductDescriptionContains(String category, String description, Pageable pageable);

    Page<Product> findProductsByProductCategoryContainsAndProductDescriptionContainsOrProductNameContains(String category, String description, String productName, Pageable pageable);

    Page<Product> findProductsByProductDescriptionContains(String description, Pageable pageable);

    Page<Product> findProductsByProductCategoryContains(String category, Pageable pageable);

    Page<Product> findProductsByProductDescriptionContainsOrProductNameContains(String description, String productName, Pageable pageable);

    List<Product> findProductsByProductCategoryContains(String category);

    List<Product> findProductsByProductDetails_ProductTypeContains(String type);

    List<Product> findProductsByProductDetails_ProductAlcoholColorContains(String color);

    List<Product> findProductsByProductDetails_ProductManufacturerContains(String manufacturer);

    List<Product> findProductsByProductDetails_ProductCountryContains(String country);

    Optional<Product> findProductByProductName(String name);

    Page<Product> findProductsByProductDetails_ProductVolumeContains(String volume, Pageable pageable);

    // FILTER requests
    Page<Product> findProductsByProductCategoryIn(List<String> categories, Pageable pageable);

    Page<Product> findProductsByProductDetails_ProductTypeIn(List<String> types, Pageable pageable);

    Page<Product> findProductsByProductDetails_ProductManufacturerContains(String manufacturer, Pageable pageable);

    Page<Product> findProductsByProductDetails_ProductCountryContains(String country, Pageable pageable);

    Page<Product> findProductsByProductDetails_ProductCountryContainsAndProductDetails_ProductManufacturerContains(String country, String manufacturer, Pageable pageable);

    Page<Product> findProductsByProductDetails_ProductTypeInAndProductDetails_ProductCountryContains(List<String> types, String country, Pageable pageable);

    Page<Product> findProductsByProductDetails_ProductTypeInAndProductDetails_ProductCountryContainsAndProductDetails_ProductManufacturerContains(List<String> types, String country, String manufacturer, Pageable pageable);

    Page<Product> findProductsByProductDetails_ProductTypeInAndProductDetails_ProductManufacturerContains(List<String> types, String manufacturer, Pageable pageable);

    Page<Product> findProductsByProductCategoryInAndProductDetails_ProductCountryContainsAndProductDetails_ProductManufacturerContains(List<String> categories, String country, String manufacturer, Pageable pageable);

    Page<Product> findProductsByProductCategoryInAndProductDetails_ProductManufacturerContains(List<String> categories, String manufacturer, Pageable pageable);

    Page<Product> findProductsByProductCategoryInAndProductDetails_ProductCountryContains(List<String> categories, String country, Pageable pageable);

    Page<Product> findProductsByProductCategoryInAndProductDetails_ProductTypeIn(List<String> categories, List<String> types, Pageable pageable);

    Page<Product> findProductsByProductCategoryInAndProductDetails_ProductTypeInAndProductDetails_ProductCountryContains(List<String> categories, List<String> types, String country, Pageable pageable);

    Page<Product> findProductsByProductCategoryInAndProductDetails_ProductTypeInAndProductDetails_ProductManufacturerContains(List<String> categories, List<String> types, String manufacturer, Pageable pageable);

    Page<Product> findProductsByProductCategoryInAndProductDetails_ProductTypeInAndProductDetails_ProductCountryContainsAndProductDetails_ProductManufacturerContains(List<String> categories, List<String> types, String country, String manufacturer, Pageable pageable);


}
