package ru.ryazan.senatorshop.core.service.impl;

import org.springframework.stereotype.Service;
import ru.ryazan.senatorshop.core.domain.details.ProductType;
import ru.ryazan.senatorshop.core.repository.TypeRepository;
import ru.ryazan.senatorshop.core.service.ProductTypeService;

import java.util.List;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    private TypeRepository repository;

    public ProductTypeServiceImpl(TypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductType> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductType> findProductTypesByTypeEquals(String type) {
        return repository.findProductTypesByTypeEquals(type);
    }

    @Override
    public void create(ProductType productType) {
        repository.save(productType);
    }
}
