package ru.gabdulindv.senatorshop.service.impl;

import org.springframework.stereotype.Service;
import ru.gabdulindv.senatorshop.repository.ProductDetailsRepo;
import ru.gabdulindv.senatorshop.service.ProductDetailsService;

import java.util.Set;

@Service
public class ProductDetailsImpl implements ProductDetailsService {
    private ProductDetailsRepo productDetailsRepo;

    public ProductDetailsImpl(ProductDetailsRepo productDetailsRepo) {
        this.productDetailsRepo = productDetailsRepo;
    }

    @Override
    public Set<String> findAllTypes() {
        return productDetailsRepo.findAllTypes();
    }

    @Override
    public Set<String> findAllManufacturers() {
        return productDetailsRepo.findAllManufacturers();
    }

    @Override
    public Set<String> findAllCountries() {
        return productDetailsRepo.findAllCountries();
    }
}
