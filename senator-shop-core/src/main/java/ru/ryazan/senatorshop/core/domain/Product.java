package ru.ryazan.senatorshop.core.domain;


import javax.persistence.*;
import java.util.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String productName;
    private String productCategory;
    private String productSubCategory;
    private String productDescription;

    public Set<ProductImage> getProductImageSet() {
        return productImageSet;
    }

    public void setProductImageSet(Set<ProductImage> productImageSet) {
        this.productImageSet = productImageSet;
    }

    private String productPrice;
    private String productUnitInStock;

    @Transient
    private String dataImg;


    @OneToMany(mappedBy = "product",
            cascade = CascadeType.ALL
    )
    private Set<ProductImage> productImageSet;


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


    public String getDataImg() {
        setDataImg();
        return dataImg;
    }

    public void setDataImg() {
        List<ProductImage> images = new ArrayList<>(productImageSet);
        this.dataImg =  Base64.getEncoder().encodeToString(images.get(0).getFileData());
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
