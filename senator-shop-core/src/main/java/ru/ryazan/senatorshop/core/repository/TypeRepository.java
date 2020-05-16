package ru.ryazan.senatorshop.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ryazan.senatorshop.core.domain.details.ProductType;

import java.util.List;

public interface TypeRepository extends JpaRepository<ProductType, Long> {
    List<ProductType> findProductTypesByTypeEquals(String type);
}
