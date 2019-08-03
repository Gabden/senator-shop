package ru.ryazan.senatorshop.core.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String productName;
    private String productCategory;
    private String productSubCategory;
    private String productDescription;
    private String productPrice;

    public Long getId() {
        return id;
    }

    public Product() {
    }

    public Product(String productName, String productCategory, String productSubCategory, String productDescription, String productPrice, String productUnitInStock) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.productSubCategory = productSubCategory;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productUnitInStock = productUnitInStock;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductSubCategory() {
        return productSubCategory;
    }

    public void setProductSubCategory(String productSubCategory) {
        this.productSubCategory = productSubCategory;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductUnitInStock() {
        return productUnitInStock;
    }

    public void setProductUnitInStock(String productUnitInStock) {
        this.productUnitInStock = productUnitInStock;
    }

    private String productUnitInStock;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", productSubCategory='" + productSubCategory + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productPrice='" + productPrice + '\'' +
                ", productUnitInStock='" + productUnitInStock + '\'' +
                '}';
    }
}
