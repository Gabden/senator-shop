package ru.gabdulindv.senatorshop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.gabdulindv.senatorshop.model.product.ProductDetails;

import java.util.Set;

public interface ProductDetailsRepo extends JpaRepository<ProductDetails, Long> {
    @Query("select p.productType from ProductDetails p")
    Set<String> findAllTypes();
}
