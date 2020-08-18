package ru.gabdulindv.senatorshop.model.order;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class ReservedCart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ReservedCartItem> reservedCartItems;

    private Integer grandTotal;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "orderId")
    private Order order;

    public ReservedCart() {
        grandTotal = 0;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public Set<ReservedCartItem> getReservedCartItems() {
        return reservedCartItems;
    }

    public void setReservedCartItems(Set<ReservedCartItem> reservedCartItems) {
        this.reservedCartItems = reservedCartItems;
    }

    public Integer getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Integer grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "ReservedCart{" +
                "cartId=" + cartId +
                ", cartItems=" + reservedCartItems +
                ", grandTotal=" + grandTotal +
                ", order=" + order +
                '}';
    }
}
