package ru.ryazan.senatorshop.core.service;

import ru.ryazan.senatorshop.core.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    void addProduct(Product product);
    void update(Optional<Product> product);
    void delete(Product product);
    void deleteById(Long id);
}
