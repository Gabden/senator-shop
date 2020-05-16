package ru.ryazan.senatorshop.core.service;

import ru.ryazan.senatorshop.core.domain.details.ProductType;

import java.util.List;

public interface ProductTypeService {
    List<ProductType> findAll();

    List<ProductType> findProductTypesByTypeEquals(String type);

    void create(ProductType productType);
}
