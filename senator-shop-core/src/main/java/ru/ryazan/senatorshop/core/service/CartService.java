package ru.ryazan.senatorshop.core.service;

import ru.ryazan.senatorshop.core.domain.cart.Cart;

import java.util.Optional;

public interface CartService {
    Cart create(Cart cart);


    Optional<Cart> read(Long id);

    void update(Long id, Cart cart);



    void delete(Long id);
}
