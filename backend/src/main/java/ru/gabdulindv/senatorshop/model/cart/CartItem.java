package ru.gabdulindv.senatorshop.model.cart;

import ru.gabdulindv.senatorshop.model.product.Product;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cart_item")
public class CartItem implements Serializable {
    private static final long serialVersionUID = 7934178160948239055L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "cartId")
    private Cart cart;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    private String cartItemPrice;
    private String cartItemFinalPrice;

    private Integer totalPrice;

    public CartItem() {
        this.quantity = 1;
        this.totalPrice = 0;
    }


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

    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemId=" + cartItemId +
                ", cart=" + cart +
                ", product=" + product +
                ", quantity=" + quantity +
                ", cartItemPrice='" + cartItemPrice + '\'' +
                ", cartItemFinalPrice='" + cartItemFinalPrice + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}