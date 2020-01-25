package ru.ryazan.senatorshop.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.ryazan.senatorshop.core.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Page<Product> findAll(Pageable pageable);
    Page<Product> findByproductCategory(String category,Pageable pageable);
    Optional<Product> findById(Long id);
    void addProduct(Product product);
    void update(Optional<Product> product);
    void delete(Product product);
    void deleteById(Long id);
    List<Product> findByproductCategory(String category);
    List<Product> findAllList();
}
