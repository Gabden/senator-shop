package ru.gabdulindv.senatorshop.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.gabdulindv.senatorshop.model.product.Product;
import ru.gabdulindv.senatorshop.repository.ProductRepo;
import ru.gabdulindv.senatorshop.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepo productRepository;

    public ProductServiceImpl(ProductRepo productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> findAll(Pageable pageable) {
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
    public Optional<Product> findProductByName(String name) {
        return productRepository.findProductByProductName(name);
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
    public Page<Product> findProductsByProductDescriptionContainsOrProductNameContains(String description, String name, Pageable pageable) {
        return productRepository.findProductsByProductDescriptionContainsOrProductNameContains(description, name, pageable);
    }

    @Override
    public Page<Product> findProductsByProductCategoryContainsAndProductDescriptionContainsOrProductNameContains(String category, String description, String productName, Pageable pageable) {
        return productRepository.findProductsByProductCategoryContainsAndProductDescriptionContainsOrProductNameContains(category, description, productName, pageable);
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
    public List<Product> findProductsByProductDetails_ProductTypeContains(String type) {
        return productRepository.findProductsByProductDetails_ProductTypeContains(type);
    }

    @Override
    public List<Product> findProductsByProductDetails_ProductAlcoholColorContains(String color) {
        return productRepository.findProductsByProductDetails_ProductAlcoholColorContains(color);
    }

    @Override
    public List<Product> findProductsByProductDetails_ProductManufacturerContains(String manufacturer) {
        return productRepository.findProductsByProductDetails_ProductManufacturerContains(manufacturer);
    }

    @Override
    public List<Product> findProductsByProductDetails_ProductCountryContains(String country) {
        return productRepository.findProductsByProductDetails_ProductCountryContains(country);
    }

    @Override
    public Page<Product> findProductsByProductCategoryIn(List<String> categories, Pageable pageable) {
        return productRepository.findProductsByProductCategoryIn(categories, pageable);
    }

    @Override
    public Page<Product> findProductsByProductCategoryInAndProductDetails_ProductManufacturerContains(List<String> categories, String manufacturer, Pageable pageable) {
        return productRepository.findProductsByProductCategoryInAndProductDetails_ProductManufacturerContains(categories, manufacturer, pageable);
    }

    @Override
    public Page<Product> findProductsByProductCategoryInAndProductDetails_ProductCountryContains(List<String> categories, String country, Pageable pageable) {
        return productRepository.findProductsByProductCategoryInAndProductDetails_ProductCountryContains(categories, country, pageable);
    }

    @Override
    public Page<Product> findProductsByProductCategoryInAndProductDetails_ProductTypeInAndProductDetails_ProductManufacturerContains(List<String> categories, List<String> types, String manufacturer, Pageable pageable) {
        return productRepository.findProductsByProductCategoryInAndProductDetails_ProductTypeInAndProductDetails_ProductManufacturerContains(categories, types, manufacturer, pageable);
    }

    @Override
    public Page<Product> findProductsByProductCategoryInAndProductDetails_ProductTypeIn(List<String> categories, List<String> types, Pageable pageable) {
        return productRepository.findProductsByProductCategoryInAndProductDetails_ProductTypeIn(categories, types, pageable);
    }

    @Override
    public Page<Product> findProductsByProductCategoryInAndProductDetails_ProductTypeInAndProductDetails_ProductCountryContains(List<String> categories, List<String> types, String country, Pageable pageable) {
        return productRepository.findProductsByProductCategoryInAndProductDetails_ProductTypeInAndProductDetails_ProductCountryContains(categories, types, country, pageable);
    }

    @Override
    public Page<Product> findProductsByProductCategoryInAndProductDetails_ProductTypeInAndProductDetails_ProductCountryContainsAndProductDetails_ProductManufacturerContains(List<String> categories, List<String> types, String country, String manufacturer, Pageable pageable) {
        return productRepository.findProductsByProductCategoryInAndProductDetails_ProductTypeInAndProductDetails_ProductCountryContainsAndProductDetails_ProductManufacturerContains(categories, types, country, manufacturer, pageable);
    }


}