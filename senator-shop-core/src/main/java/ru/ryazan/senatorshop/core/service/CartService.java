package ru.ryazan.senatorshop.core.service;

import ru.ryazan.senatorshop.core.domain.cart.Cart;

import java.io.IOException;
import java.util.Optional;

public interface CartService {
    Cart create(Cart cart);


    Optional<Cart> read(Long id);

    void update(Cart cart);
    Cart validate(Long cartId) throws IOException;



    void delete(Long id);
}
