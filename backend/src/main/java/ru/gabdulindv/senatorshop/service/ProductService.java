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

}