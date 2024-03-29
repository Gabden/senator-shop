package ru.gabdulindv.senatorshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.gabdulindv.senatorshop.model.cart.CartItem;
import ru.gabdulindv.senatorshop.model.order.ReservedCartItem;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product implements Serializable {
    private static final long serialVersionUID = -1530468945225710892L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_category")
    private String productCategory;

    @Column(name = "product_description", length = 5000)
    private String productDescription;

    @Column(name = "product_price")
    private String productPrice;

    @Column(name = "sale_price")
    private String productSalePrice;

    @Column(name = "product_discount")
    private int discount;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "details_id")
    private ProductDetails productDetails;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_image_id")
    @JsonIgnore
    private ProductImage productImage;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<CartItem> cartItems;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<ReservedCartItem> reservedCartItems;

    public Product() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public String getProductSalePrice() {
        return productSalePrice;
    }

    public void setProductSalePrice(String productSalePrice) {
        this.productSalePrice = productSalePrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    public ProductImage getProductImage() {
        return productImage;
    }

    public void setProductImage(ProductImage productImageSet) {
        this.productImage = productImageSet;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Set<ReservedCartItem> getReservedCartItems() {
        return reservedCartItems;
    }

    public void setReservedCartItems(Set<ReservedCartItem> reservedCartItems) {
        this.reservedCartItems = reservedCartItems;
    }
}
