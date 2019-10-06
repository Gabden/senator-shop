package ru.ryazan.senatorshop.core.service;

import ru.ryazan.senatorshop.core.domain.Customer;
import ru.ryazan.senatorshop.core.domain.CustomerOrder;
import ru.ryazan.senatorshop.core.domain.Product;
import ru.ryazan.senatorshop.core.domain.cart.Cart;
import ru.ryazan.senatorshop.core.domain.cart.CartItem;

import java.util.List;
import java.util.Optional;

public interface CartItemService {
    Optional<CartItem> findById(Long id);
    void addItem(CartItem item);
    void delete(CartItem item);
    void deleteById(Long id);
    void deleteAll(Cart cart);
    void deleteAllInOrder(Long cartId);
    void deleteCartItemByProductId(Long id);
}
