package ru.ryazan.gabden.senatorshop.service.impl;

import org.springframework.stereotype.Service;
import ru.ryazan.gabden.senatorshop.domain.Product;
import ru.ryazan.gabden.senatorshop.repository.ProductRepository;
import ru.ryazan.gabden.senatorshop.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }
}
