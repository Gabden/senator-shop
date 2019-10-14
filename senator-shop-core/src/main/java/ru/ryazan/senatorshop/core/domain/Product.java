package ru.ryazan.senatorshop.core.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.ryazan.senatorshop.core.domain.cart.CartItem;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;

@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = -1530468945225710892L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productName;

    private String productCategory;

    private String productCountry;

    private String productRegion;

    private String productAlcoholDegree;

    private String productAlcoholColor;

    private String productAlcoholSugar;

    private String productAlcoholTemperature;

    private String productAlcoholSort;
    private String productManufacturer;

    @Column(length = 1500)
    private String productDescription;


    private String productMature;

    private String productTaste;


    public Set<ProductImage> getProductImageSet() {
        return productImageSet;
    }

    private String productPrice;

    private String productUnitInStock;

    @Transient
    private String dataImg;


    @OneToMany(mappedBy = "product",
            cascade = CascadeType.ALL
    )
    @JsonIgnore
    private Set<ProductImage> productImageSet;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<CartItem> cartItems;


    public void setProductImageSet(Set<ProductImage> productImageSet) {
        this.productImageSet = productImageSet;
    }

    public Long getId() {
        return id;
    }

    public Product() {
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        if (this.cartItems == null) {
            this.cartItems = cartItems;
        } else {
            this.cartItems.retainAll(cartItems);
            this.cartItems.addAll(cartItems);
        }

    }

    public String getDataImg() {
        setDataImg();
        return dataImg;
    }

    public void setDataImg() {
        List<ProductImage> images = new ArrayList<>(productImageSet);
       if (images.size() != 0){
           this.dataImg =  Base64.getEncoder().encodeToString(images.get(0).getFileData());
       }
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
                ", productDescription='" + productDescription + '\'' +
                ", productPrice='" + productPrice + '\'' +
                ", productUnitInStock='" + productUnitInStock + '\'' +
                '}';
    }
}
