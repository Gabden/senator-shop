package ru.ryazan.senatorshop.core.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import ru.ryazan.senatorshop.core.domain.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
}
