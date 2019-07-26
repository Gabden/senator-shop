package ru.ryazan.senatorshop.core.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.ryazan.senatorshop.core.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
