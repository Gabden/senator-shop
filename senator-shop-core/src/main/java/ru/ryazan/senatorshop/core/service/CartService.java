package ru.ryazan.senatorshop.core.service;

import ru.ryazan.senatorshop.core.domain.cart.Cart;

public interface CartService {
    Cart create(Cart cart);
    Cart read(String id);
    void update(String id, Cart cart);
    void delete(String id);
}
