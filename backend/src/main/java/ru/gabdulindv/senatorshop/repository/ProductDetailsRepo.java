package ru.gabdulindv.senatorshop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.gabdulindv.senatorshop.model.product.ProductDetails;

public interface ProductDetailsRepo extends JpaRepository<ProductDetails, Long> {
}
