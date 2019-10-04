package ru.ryazan.senatorshop.core.domain.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.ryazan.senatorshop.core.domain.Customer;
import ru.ryazan.senatorshop.core.domain.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
@Entity
public class Cart implements Serializable {

    private static final long serialVersionUID = 1154447935307492308L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cartId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Map<Long, CartItem> cartItems;

    private Integer grandTotal;

    @OneToOne(cascade = {CascadeType.ALL})
    @JsonIgnore
    @JoinColumn(name = "customerId")
    private Customer customer;

    public Cart() {
        cartItems = new HashMap<>();
        grandTotal = 0;
    }


    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Map<Long, CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<Long, CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Integer getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Integer grandTotal) {
        this.grandTotal = grandTotal;
    }

    public void addCartItem(CartItem item) {
        Long productId = item.getProduct().getId();
        if (cartItems.containsKey(productId)) {
            CartItem existingItem = cartItems.get(productId);
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
            cartItems.put(productId, existingItem);
        } else {
            cartItems.put(productId, item);
        }

        updateGrandTotal();
    }

    public void removeCartItem(CartItem item){
        Long productId = item.getProduct().getId();
        cartItems.remove(productId);
        updateGrandTotal();
    }

    private void updateGrandTotal() {
        grandTotal = 0;
        for (CartItem item : cartItems.values()){
            grandTotal += item.getTotalPrice();
        }
    }
}
