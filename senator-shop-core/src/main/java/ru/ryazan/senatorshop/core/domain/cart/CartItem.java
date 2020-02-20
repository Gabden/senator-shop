package ru.ryazan.senatorshop.core.domain.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.ryazan.senatorshop.core.domain.Product;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CartItem implements Serializable {
    private static final long serialVersionUID = 7934178160948239055L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartItemId;

    @ManyToOne
    @JoinColumn(name = "cartId")
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "id")
    @JsonIgnore
    private Product product;
    private int quantity;

    private String cartItemPrice;
    private String cartItemFinalPrice;

    public String getCartItemPrice() {
        return cartItemPrice;
    }

    public void setCartItemPrice(String cartItemPrice) {
        this.cartItemPrice = cartItemPrice;
    }

    public String getCartItemFinalPrice() {
        return cartItemFinalPrice;
    }

    public void setCartItemFinalPrice(String cartItemFinalPrice) {
        this.cartItemFinalPrice = cartItemFinalPrice;
    }

    @JsonIgnore
    private Integer totalPrice;

    public CartItem() {
    }

    public CartItem(Product product) {
        this.product = product;
        this.quantity = 1;
        this.totalPrice = 0;
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return Integer.parseInt(cartItemFinalPrice) * quantity;
    }

    public int getTotalPriceFull() {
        return Integer.parseInt(cartItemPrice) * quantity;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }
}
