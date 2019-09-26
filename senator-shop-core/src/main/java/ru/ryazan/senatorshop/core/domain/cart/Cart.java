package ru.ryazan.senatorshop.core.domain.cart;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private String cartId;
    private Map<Long, CartItem> cartItems;
    private Integer grandTotal;

    public Cart() {
        cartItems = new HashMap<>();
        grandTotal = 0;
    }

    public Cart(String cartId) {
        this();
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
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
