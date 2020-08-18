package ru.gabdulindv.senatorshop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.gabdulindv.senatorshop.model.product.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
