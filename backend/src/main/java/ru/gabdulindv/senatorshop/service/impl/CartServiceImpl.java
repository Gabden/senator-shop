package ru.gabdulindv.senatorshop.service.impl;

import org.springframework.stereotype.Service;
import ru.gabdulindv.senatorshop.model.cart.Cart;
import ru.gabdulindv.senatorshop.repository.CartRepo;
import ru.gabdulindv.senatorshop.service.CartService;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private CartRepo cartRepo;

    public CartServiceImpl(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepo.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        cartRepo.deleteById(id);
    }

    @Override
    public void saveOrUpdate(Cart cart) {
        cartRepo.save(cart);
    }
}
