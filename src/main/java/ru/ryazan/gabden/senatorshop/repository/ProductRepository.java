package ru.ryazan.gabden.senatorshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ryazan.gabden.senatorshop.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
