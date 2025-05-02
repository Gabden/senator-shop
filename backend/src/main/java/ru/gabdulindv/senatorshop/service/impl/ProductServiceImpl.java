package ru.gabdulindv.senatorshop.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.gabdulindv.senatorshop.model.product.Product;
import ru.gabdulindv.senatorshop.repository.ProductRepo;
import ru.gabdulindv.senatorshop.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepo productRepository;
    private List<Sort.Order> sortOrder;

    public ProductServiceImpl(ProductRepo productRepository) {
        this.productRepository = productRepository;
        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        Sort.Order orderOutOfStock = new Sort.Order(Sort.Direction.ASC, "productDetails.isOutOfStock");
        Sort.Order orderId = new Sort.Order(Sort.Direction.DESC, "productId");
        orders.add(orderOutOfStock);
        orders.add(orderId);
        sortOrder = orders;
    }

    private Pageable getLocalPageable(Pageable pageable) {
      int pageNumber = 0;

      if (pageable != null) {
        pageNumber = pageable.getPageNumber();
      }

      return PageRequest.of(pageNumber, 8, Sort.by(sortOrder));
    }

    public Page<Product> findAll(Pageable pageable) {
     return productRepository.findAll(getLocalPageable(pageable));
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
        return productRepository.findProductsByProductCategoryContains(category, getLocalPageable(pageable));
    }

    @Override
    public Page<Product> findProductsByProductCategoryContainsAndProductDescriptionContains(String category, String description, Pageable pageable) {
        return productRepository.findProductsByProductCategoryContainsAndProductDescriptionContains(category, description, getLocalPageable(pageable));
    }

    @Override
    public Page<Product> findProductsByProductDescriptionContains(String description, Pageable pageable) {
        return productRepository.findProductsByProductDescriptionContains(description, getLocalPageable(pageable));
    }

    @Override
    public Page<Product> findProductsByProductDescriptionContainsOrProductNameContains(String description, String name, Pageable pageable) {
        return productRepository.findProductsByProductDescriptionContainsOrProductNameContains(description, name, getLocalPageable(pageable));
    }

    @Override
    public Page<Product> findProductsByProductCategoryContainsAndProductDescriptionContainsOrProductNameContains(String category, String description, String productName, Pageable pageable) {
        return productRepository.findProductsByProductCategoryContainsAndProductDescriptionContainsOrProductNameContains(category, description, productName, getLocalPageable(pageable));
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll(Sort.by(sortOrder));
    }

    @Override
    public List<Product> findProductsByProductCategoryContains(String category) {
        return productRepository.findProductsByProductCategoryContains(category, Sort.by(sortOrder));
    }

    @Override
    public List<Product> findProductsByProductDetails_ProductTypeContains(String type) {
        return productRepository.findProductsByProductDetails_ProductTypeContains(type, Sort.by(sortOrder));
    }

    @Override
    public List<Product> findProductsByProductDetails_ProductAlcoholColorContains(String color) {
        return productRepository.findProductsByProductDetails_ProductAlcoholColorContains(color, Sort.by(sortOrder));
    }

    @Override
    public List<Product> findProductsByProductDetails_ProductManufacturerContains(String manufacturer) {
        return productRepository.findProductsByProductDetails_ProductManufacturerContains(manufacturer, Sort.by(sortOrder));
    }

    @Override
    public List<Product> findProductsByProductDetails_ProductCountryContains(String country) {
        return productRepository.findProductsByProductDetails_ProductCountryContains(country, Sort.by(sortOrder));
    }

    @Override
    public Page<Product> findProductsByProductDetails_ProductVolumeContains(String volume, Pageable pageable) {
        return productRepository.findProductsByProductDetails_ProductVolumeContains(volume, getLocalPageable(pageable));
    }

    @Override
    public Page<Product> findProductsByProductDetails_ProductAlcoholColorContains(String color, Pageable pageable) {
        return productRepository.findProductsByProductDetails_ProductAlcoholColorContains(color, getLocalPageable(pageable));
    }

    @Override
    public Page<Product> findProductsByProductDetails_ProductRegionContains(String region, Pageable pageable) {
        return productRepository.findProductsByProductDetails_ProductRegionContains(region, getLocalPageable(pageable));
    }

    @Override
    public Page<Product> findProductsByProductDetails_ProductAlcoholDegreeContains(String degree, Pageable pageable) {
        return productRepository.findProductsByProductDetails_ProductAlcoholDegreeContains(degree, getLocalPageable(pageable));
    }

    @Override
    public Page<Product> findProductsByProductDetails_ProductAlcoholSortContains(String sort, Pageable pageable) {
        return productRepository.findProductsByProductDetails_ProductAlcoholSortContains(sort, getLocalPageable(pageable));
    }

    @Override
    public Page<Product> findProductsByProductDetails_ProductAlcoholSugarContains(String sugar, Pageable pageable) {
        return productRepository.findProductsByProductDetails_ProductAlcoholSugarContains(sugar, getLocalPageable(pageable));
    }

    @Override
    public Page<Product> findProductsByProductDetails_ProductAlcoholTemperatureContains(String temperature, Pageable pageable) {
        return productRepository.findProductsByProductDetails_ProductAlcoholTemperatureContains(temperature, getLocalPageable(pageable));
    }

    @Override
    public Page<Product> findProductsByProductDetails_ProductMatureContains(String mature, Pageable pageable) {
        return productRepository.findProductsByProductDetails_ProductMatureContains(mature, getLocalPageable(pageable));
    }

    @Override
    public Page<Product> findProductsByProductCategoryIn(List<String> categories, Pageable pageable) {
        return productRepository.findProductsByProductCategoryIn(categories, getLocalPageable(pageable));
    }

    @Override
    public Page<Product> findProductsByProductCategoryInAndProductDetails_ProductManufacturerContains(List<String> categories, String manufacturer, Pageable pageable) {
        return productRepository.findProductsByProductCategoryInAndProductDetails_ProductManufacturerContains(categories, manufacturer, getLocalPageable(pageable));
    }

    @Override
    public Page<Product> findProductsByProductCategoryInAndProductDetails_ProductCountryContains(List<String> categories, String country, Pageable pageable) {
        return productRepository.findProductsByProductCategoryInAndProductDetails_ProductCountryContains(categories, country, getLocalPageable(pageable));
    }

    @Override
    public Page<Product> findProductsByProductCategoryInAndProductDetails_ProductTypeInAndProductDetails_ProductManufacturerContains(List<String> categories, List<String> types, String manufacturer, Pageable pageable) {
        return productRepository.findProductsByProductCategoryInAndProductDetails_ProductTypeInAndProductDetails_ProductManufacturerContains(categories, types, manufacturer, getLocalPageable(pageable));
    }

    @Override
    public Page<Product> findProductsByProductCategoryInAndProductDetails_ProductTypeIn(List<String> categories, List<String> types, Pageable pageable) {
        return productRepository.findProductsByProductCategoryInAndProductDetails_ProductTypeIn(categories, types, getLocalPageable(pageable));
    }

    @Override
    public Page<Product> findProductsByProductCategoryInAndProductDetails_ProductTypeInAndProductDetails_ProductCountryContains(List<String> categories, List<String> types, String country, Pageable pageable) {
        return productRepository.findProductsByProductCategoryInAndProductDetails_ProductTypeInAndProductDetails_ProductCountryContains(categories, types, country, getLocalPageable(pageable));
    }

    @Override
    public Page<Product> findProductsByProductCategoryInAndProductDetails_ProductTypeInAndProductDetails_ProductCountryContainsAndProductDetails_ProductManufacturerContains(List<String> categories, List<String> types, String country, String manufacturer, Pageable pageable) {
        return productRepository.findProductsByProductCategoryInAndProductDetails_ProductTypeInAndProductDetails_ProductCountryContainsAndProductDetails_ProductManufacturerContains(categories, types, country, manufacturer, getLocalPageable(pageable));
    }

    @Override
    public Page<Product> findProductsByProductDetails_ProductTypeIn(List<String> types, Pageable pageable) {
        return productRepository.findProductsByProductDetails_ProductTypeIn(types, getLocalPageable(pageable));
    }

    @Override
    public Page<Product> findProductsByProductDetails_ProductManufacturerContains(String manufacturer, Pageable pageable) {
        return productRepository.findProductsByProductDetails_ProductManufacturerContains(manufacturer, getLocalPageable(pageable));
    }

    @Override
    public Page<Product> findProductsByProductDetails_ProductCountryContains(String country, Pageable pageable) {
        return productRepository.findProductsByProductDetails_ProductCountryContains(country, getLocalPageable(pageable));
    }

    @Override
    public Page<Product> findProductsByProductDetails_ProductCountryContainsAndProductDetails_ProductManufacturerContains(String country, String manufacturer, Pageable pageable) {
        return productRepository.findProductsByProductDetails_ProductCountryContainsAndProductDetails_ProductManufacturerContains(country, manufacturer, getLocalPageable(pageable));
    }

    @Override
    public Page<Product> findProductsByProductDetails_ProductTypeInAndProductDetails_ProductCountryContains(List<String> types, String country, Pageable pageable) {
        return productRepository.findProductsByProductDetails_ProductTypeInAndProductDetails_ProductCountryContains(types, country, getLocalPageable(pageable));
    }

    @Override
    public Page<Product> findProductsByProductDetails_ProductTypeInAndProductDetails_ProductCountryContainsAndProductDetails_ProductManufacturerContains(List<String> types, String country, String manufacturer, Pageable pageable) {
        return productRepository.findProductsByProductDetails_ProductTypeInAndProductDetails_ProductCountryContainsAndProductDetails_ProductManufacturerContains(types, country, manufacturer, getLocalPageable(pageable));
    }

    @Override
    public Page<Product> findProductsByProductDetails_ProductTypeInAndProductDetails_ProductManufacturerContains(List<String> types, String manufacturer, Pageable pageable) {
        return productRepository.findProductsByProductDetails_ProductTypeInAndProductDetails_ProductManufacturerContains(types, manufacturer, getLocalPageable(pageable));
    }

    @Override
    public Page<Product> findProductsByProductCategoryInAndProductDetails_ProductCountryContainsAndProductDetails_ProductManufacturerContains(List<String> categories, String country, String manufacturer, Pageable pageable) {
        return productRepository.findProductsByProductCategoryInAndProductDetails_ProductCountryContainsAndProductDetails_ProductManufacturerContains(categories, country, manufacturer, getLocalPageable(pageable));
    }


}