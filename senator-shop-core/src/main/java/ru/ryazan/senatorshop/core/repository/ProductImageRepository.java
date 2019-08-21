package ru.ryazan.senatorshop.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ryazan.senatorshop.core.domain.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
