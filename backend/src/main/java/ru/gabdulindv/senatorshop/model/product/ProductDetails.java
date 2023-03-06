package ru.gabdulindv.senatorshop.model.product;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_details")
public class ProductDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_type")
    private String productType;
    @Column(name = "product_country")
    private String productCountry;
    @Column(name = "product_region")
    private String productRegion;
    @Column(name = "product_alcohol_degree")
    private String productAlcoholDegree;
    @Column(name = "product_alcohol_color")
    private String productAlcoholColor;
    @Column(name = "product_alcohol_sugar")
    private String productAlcoholSugar;
    @Column(name = "product_alcohol_temperature")
    private String productAlcoholTemperature;
    @Column(name = "product_alcohol_sort")
    private String productAlcoholSort;
    @Column(name = "product_manufacturer")
    private String productManufacturer;
    @Column(name = "product_volume")
    private String productVolume;
    @Column(name = "product_mature")
    private String productMature;
    @Column(name = "product_taste", length = 5000)
    private String productTaste;
    @Column(name = "product_unit_in_stock")
    private String productUnitInStock;

    @Column(name = "product_is_out_of_stock")
    private Boolean isOutOfStock;

//    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//    @JoinColumn(name = "product_id")
//    private Product product;

    public ProductDetails() {
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

    public Boolean getOutOfStock() {
        return isOutOfStock;
    }

    public void setOutOfStock(Boolean outOfStock) {
        isOutOfStock = outOfStock;
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
