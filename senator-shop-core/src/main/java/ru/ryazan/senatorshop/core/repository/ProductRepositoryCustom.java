package ru.ryazan.senatorshop.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ryazan.senatorshop.core.domain.Product;

import java.util.List;

public interface ProductRepositoryCustom extends JpaRepository<Product,Long> {
    List<Product> findByproductCategory(String category);
}
