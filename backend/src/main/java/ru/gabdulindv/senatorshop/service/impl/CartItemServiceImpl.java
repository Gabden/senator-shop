package ru.gabdulindv.senatorshop.service.impl;

import org.springframework.stereotype.Service;
import ru.gabdulindv.senatorshop.model.cart.CartItem;
import ru.gabdulindv.senatorshop.repository.CartItemRepo;
import ru.gabdulindv.senatorshop.service.CartItemService;

import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    private CartItemRepo cartItemRepo;

    public CartItemServiceImpl(CartItemRepo cartItemRepo) {
        this.cartItemRepo = cartItemRepo;
    }

    @Override
    public Optional<CartItem> findById(Long id) {
        return cartItemRepo.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        cartItemRepo.deleteById(id);
    }

    @Override
    public void saveOrUpdate(CartItem cartItem) {
        cartItemRepo.save(cartItem);
    }
}
