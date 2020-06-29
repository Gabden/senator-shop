package ru.gabdulindv.senatorshop.model.product;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ProductDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productType;

    private String productCountry;

    private String productRegion;

    private String productAlcoholDegree;

    private String productAlcoholColor;

    private String productAlcoholSugar;

    private String productAlcoholTemperature;

    private String productAlcoholSort;

    private String productManufacturer;

    private String productVolume;

    private String productMature;

    private String productTaste;

    private String productUnitInStock;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "productId")
    private Product product;

    public ProductDetails() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductCountry() {
        return productCountry;
    }

    public void setProductCountry(String productCountry) {
        this.productCountry = productCountry;
    }

    public String getProductRegion() {
        return productRegion;
    }

    public void setProductRegion(String productRegion) {
        this.productRegion = productRegion;
    }

    public String getProductAlcoholDegree() {
        return productAlcoholDegree;
    }

    public void setProductAlcoholDegree(String productAlcoholDegree) {
        this.productAlcoholDegree = productAlcoholDegree;
    }

    public String getProductAlcoholColor() {
        return productAlcoholColor;
    }

    public void setProductAlcoholColor(String productAlcoholColor) {
        this.productAlcoholColor = productAlcoholColor;
    }

    public String getProductAlcoholSugar() {
        return productAlcoholSugar;
    }

    public void setProductAlcoholSugar(String productAlcoholSugar) {
        this.productAlcoholSugar = productAlcoholSugar;
    }

    public String getProductAlcoholTemperature() {
        return productAlcoholTemperature;
    }

    public void setProductAlcoholTemperature(String productAlcoholTemperature) {
        this.productAlcoholTemperature = productAlcoholTemperature;
    }

    public String getProductAlcoholSort() {
        return productAlcoholSort;
    }

    public void setProductAlcoholSort(String productAlcoholSort) {
        this.productAlcoholSort = productAlcoholSort;
    }

    public String getProductManufacturer() {
        return productManufacturer;
    }

    public void setProductManufacturer(String productManufacturer) {
        this.productManufacturer = productManufacturer;
    }

    public String getProductVolume() {
        return productVolume;
    }

    public void setProductVolume(String productVolume) {
        this.productVolume = productVolume;
    }

    public String getProductMature() {
        return productMature;
    }

    public void setProductMature(String productMature) {
        this.productMature = productMature;
    }

    public String getProductTaste() {
        return productTaste;
    }

    public void setProductTaste(String productTaste) {
        this.productTaste = productTaste;
    }

    public String getProductUnitInStock() {
        return productUnitInStock;
    }

    public void setProductUnitInStock(String productUnitInStock) {
        this.productUnitInStock = productUnitInStock;
    }

    @Override
    public String toString() {
        return "ProductDetails{" +
                "id=" + id +
                ", productType='" + productType + '\'' +
                ", productCountry='" + productCountry + '\'' +
                ", productRegion='" + productRegion + '\'' +
                ", productAlcoholDegree='" + productAlcoholDegree + '\'' +
                ", productAlcoholColor='" + productAlcoholColor + '\'' +
                ", productAlcoholSugar='" + productAlcoholSugar + '\'' +
                ", productAlcoholTemperature='" + productAlcoholTemperature + '\'' +
                ", productAlcoholSort='" + productAlcoholSort + '\'' +
                ", productManufacturer='" + productManufacturer + '\'' +
                ", productVolume='" + productVolume + '\'' +
                ", productMature='" + productMature + '\'' +
                ", productTaste='" + productTaste + '\'' +
                ", productUnitInStock='" + productUnitInStock + '\'' +
                '}';
    }
}
