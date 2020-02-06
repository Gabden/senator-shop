package ru.ryazan.senatorshop.core.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.ryazan.senatorshop.core.domain.Product;
import ru.ryazan.senatorshop.core.repository.ProductRepository;
import ru.ryazan.senatorshop.core.repository.ProductRepositoryCustom;
import ru.ryazan.senatorshop.core.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductRepositoryCustom productRepositoryCustom;

    public ProductServiceImpl(ProductRepository productRepository,ProductRepositoryCustom productRepositoryCustom) {
        this.productRepository = productRepository;
        this.productRepositoryCustom = productRepositoryCustom;
    }

    public Page<Product> findAll(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findByproductCategory(String category, Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        List<Product> filteredList = products.stream().filter(product -> product.getProductCategory().contains(category)).collect(Collectors.toList());
        products = new PageImpl<>(filteredList, pageable, filteredList.size());
        return products;
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
    public List<Product> findByproductCategory(String category) {
        return productRepositoryCustom.findByproductCategory(category);
    }

    @Override
    public List<Product> findAllList() {
        return productRepositoryCustom.findAll();
    }

    @Override
    public Page<Product> findProductsByProductCategoryContainsAndProductDescriptionContains(String category, String description, Pageable pageable) {
        return productRepository.findProductsByProductCategoryContainsAndProductDescriptionContains(category,description,pageable);
    }

    @Override
    public Page<Product> findProductsByProductDescriptionContains(String description, Pageable pageable) {
        return productRepository.findProductsByProductDescriptionContains(description,pageable);
    }
}
