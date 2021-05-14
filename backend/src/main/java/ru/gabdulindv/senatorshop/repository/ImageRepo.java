package ru.gabdulindv.senatorshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gabdulindv.senatorshop.model.product.ProductImage;

public interface ImageRepo extends JpaRepository<ProductImage, Long> {
}

