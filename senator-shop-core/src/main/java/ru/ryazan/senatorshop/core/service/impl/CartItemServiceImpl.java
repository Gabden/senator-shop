package ru.ryazan.senatorshop.core.service.impl;

import org.springframework.stereotype.Service;
import ru.ryazan.senatorshop.core.domain.Product;
import ru.ryazan.senatorshop.core.domain.cart.Cart;
import ru.ryazan.senatorshop.core.domain.cart.CartItem;
import ru.ryazan.senatorshop.core.repository.CartItemRepository;
import ru.ryazan.senatorshop.core.service.CartItemService;

import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    private CartItemRepository cartItemRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }



    @Override
    public Optional<CartItem> findById(Long id) {
        return cartItemRepository.findById(id);
    }

    @Override
    public void addItem(CartItem item) {
        cartItemRepository.save(item);
    }


    @Override
    public void delete(CartItem item) {
        cartItemRepository.delete(item);
    }

    @Override
    public void deleteById(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public void deleteAll(Cart cart) {
        for(CartItem item: cart.getCartItems()){
            deleteById(item.getCartItemId());
        }
    }

    @Override
    public void deleteCartItemByProductId(Long id) {
        cartItemRepository.deleteCartItemByProductId(id);
    }
}
