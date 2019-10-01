package ru.ryazan.senatorshop.core.domain.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.ryazan.senatorshop.core.domain.Product;

public class CartItem {
    @JsonIgnore
    private Product product;
    private int quantity;
    private Integer totalPrice;

    public CartItem() {
    }

    public CartItem(Product product) {
        this.product = product;
        this.quantity = 1;
        this.totalPrice = Integer.parseInt(product.getProductPrice());
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

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }
}
