package ru.ryazan.senatorshop.core.service.impl;

import org.springframework.stereotype.Service;
import ru.ryazan.senatorshop.core.domain.cart.Cart;
import ru.ryazan.senatorshop.core.repository.CartRepository;
import ru.ryazan.senatorshop.core.service.CartService;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private Map<Long, Cart> listOfCarts;
    private CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;

    }



    @Override
    public Cart create(Cart cart) {

        cartRepository.save(cart);

        return cart;
    }

    @Override
    public Optional<Cart> read(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public void update(Cart cart) {

    }

    @Override
    public Cart validate(Long cartId) throws IOException {
        Cart cart = cartRepository.getOne(cartId);
        if (cart == null || cart.getCartItems().size() == 0){
            throw new IOException(cartId + "");
        }
        update(cart);
        return cart;
    }

    @Override
    public void delete(Long id) {

    }
}
