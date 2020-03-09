package ru.ryazan.senatorshop.core.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.ryazan.senatorshop.core.domain.Product;
import ru.ryazan.senatorshop.core.repository.ProductRepository;
import ru.ryazan.senatorshop.core.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> findAll(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void update(Optional<Product> product) {

        productRepository.save(product.get());
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> findProductsByProductCategoryContains(String category, Pageable pageable) {
        return productRepository.findProductsByProductCategoryContains(category, pageable);
    }

    @Override
    public Page<Product> findProductsByProductCategoryContainsAndProductDescriptionContains(String category, String description, Pageable pageable) {
        return productRepository.findProductsByProductCategoryContainsAndProductDescriptionContains(category, description, pageable);
    }

    @Override
    public Page<Product> findProductsByProductDescriptionContains(String description, Pageable pageable) {
        return productRepository.findProductsByProductDescriptionContains(description, pageable);
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public List<Product> findProductsByProductCategoryContains(String category) {
        return productRepository.findProductsByProductCategoryContains(category);
    }

    @Override
    public List<Product> findProductsByProductManufacturerContains(String manufacturer) {
        return productRepository.findProductsByProductManufacturerContains(manufacturer);
    }

    @Override
    public List<Product> findProductsByProductTypeContains(String productType) {
        return productRepository.findProductsByProductTypeContains(productType);
    }

    @Override
    public List<Product> findProductsByProductCountryContains(String country) {
        return productRepository.findProductsByProductCountryContains(country);
    }


}
