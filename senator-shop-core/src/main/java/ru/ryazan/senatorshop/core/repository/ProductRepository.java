package ru.ryazan.senatorshop.core.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.ryazan.senatorshop.core.domain.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Page<Product> findProductsByProductCategoryContainsAndProductDescriptionContains(String category, String description, Pageable pageable);
    Page<Product> findProductsByProductDescriptionContains(String description, Pageable pageable);

    Page<Product> findProductsByProductCategoryContains(String category, Pageable pageable);

}
