package ru.ryazan.senatorshop.core.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.ryazan.senatorshop.core.domain.Product;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Page<Product> findByproductCategory(String category, Pageable pageable);

    List<Product> findByProductCategoryAndProductDescription(String category, String description);

    List<Product> findAllByProductCategory(String category);

}
