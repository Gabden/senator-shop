package ru.gabdulindv.senatorshop.model.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.gabdulindv.senatorshop.model.product.Product;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ReservedCartItem implements Serializable {
    private static final long serialVersionUID = 7934178160948239055L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservedProductId;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "cartId")
    @JsonIgnore
    private ReservedCart cart;
    //
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    private String cartItemPrice;
    private String cartItemFinalPrice;

    private Integer totalPrice;

    public ReservedCartItem() {
        this.quantity = 1;
        this.totalPrice = 0;
    }

    public Long getReservedProductId() {
        return reservedProductId;
    }

    public void setReservedProductId(Long reservedProductId) {
        this.reservedProductId = reservedProductId;
    }

    public ReservedCart getCart() {
        return cart;
    }

    public void setCart(ReservedCart cart) {
        this.cart = cart;
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

    public Long getId() {
        return reservedProductId;
    }

    public void setId(Long id) {
        this.reservedProductId = id;
    }

//    public ReservedCart getCart() {
//        return cart;
//    }
//
//    public void setCart(ReservedCart cart) {
//        this.cart = cart;
//    }

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
        return "ReservedCartItem{" +
                "id=" + reservedProductId +
                ", product=" + product +
                ", quantity=" + quantity +
                ", cartItemPrice='" + cartItemPrice + '\'' +
                ", cartItemFinalPrice='" + cartItemFinalPrice + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
