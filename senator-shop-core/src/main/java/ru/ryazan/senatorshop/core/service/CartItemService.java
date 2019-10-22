package ru.ryazan.senatorshop.core.service;

import ru.ryazan.senatorshop.core.domain.cart.Cart;
import ru.ryazan.senatorshop.core.domain.cart.CartItem;

import java.util.Optional;

public interface CartItemService {
    Optional<CartItem> findById(Long id);
    void update(CartItem item);
    void addItem(CartItem item);
    void delete(CartItem item);
    void deleteById(Long id);
    void deleteAll(Cart cart);
    void deleteAllInOrder(Long cartId);

    void replaceCarts(Cart cart);

    void deleteCartItemByProductId(Long id);
}
