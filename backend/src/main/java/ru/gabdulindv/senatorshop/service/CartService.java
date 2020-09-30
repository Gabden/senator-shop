package ru.gabdulindv.senatorshop.service;

import ru.gabdulindv.senatorshop.model.cart.Cart;

import java.util.Optional;

public interface CartService {
    Optional<Cart> findById(Long id);

    void deleteById(Long id);

    void saveOrUpdate(Cart cart);
}
