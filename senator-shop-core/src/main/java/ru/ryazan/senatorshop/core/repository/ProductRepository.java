package ru.ryazan.senatorshop.core.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.ryazan.senatorshop.core.domain.Product;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Page<Product> findProductsByProductCategoryContainsAndProductDescriptionContains(String category, String description, Pageable pageable);

    Page<Product> findProductsByProductCategoryContainsAndProductDescriptionContainsOrProductNameContains(String category, String description, String productName, Pageable pageable);

    Page<Product> findProductsByProductDescriptionContains(String description, Pageable pageable);

    Page<Product> findProductsByProductCategoryContains(String category, Pageable pageable);

    Page<Product> findProductsByProductDescriptionContainsOrProductNameContains(String description, String productName, Pageable pageable);

    List<Product> findProductsByProductCategoryContains(String category);

    List<Product> findProductsByProductManufacturerContains(String manufacturer);

    List<Product> findProductsByProductTypeContains(String productType);

    List<Product> findProductsByProductCountryContains(String country);

}
