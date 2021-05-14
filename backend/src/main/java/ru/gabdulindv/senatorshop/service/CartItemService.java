package ru.gabdulindv.senatorshop.service;

import ru.gabdulindv.senatorshop.model.cart.CartItem;

import java.util.Optional;

public interface CartItemService {
    Optional<CartItem> findById(Long id);

    void deleteById(Long id);

    void saveOrUpdate(CartItem cartItem);
}
