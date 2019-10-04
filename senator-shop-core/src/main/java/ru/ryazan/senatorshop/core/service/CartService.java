package ru.ryazan.senatorshop.core.service;

import ru.ryazan.senatorshop.core.domain.cart.Cart;

public interface CartService {
    Cart create(Cart cart);


    Cart read(Long id);

    void update(Long id, Cart cart);



    void delete(Long id);
}
