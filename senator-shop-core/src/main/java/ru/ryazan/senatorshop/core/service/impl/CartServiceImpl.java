package ru.ryazan.senatorshop.core.service.impl;

import org.springframework.stereotype.Service;
import ru.ryazan.senatorshop.core.domain.cart.Cart;
import ru.ryazan.senatorshop.core.repository.CartRepository;
import ru.ryazan.senatorshop.core.service.CartService;

import java.util.Map;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private Map<Long, Cart> listOfCarts;
    private CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;

    }

//    public CartServiceImpl() {
//        listOfCarts = new HashMap<>();
//    }

    @Override
    public Cart create(Cart cart) {
//        if (listOfCarts.containsKey(cart.getCartId())){
//            throw new IllegalArgumentException(String.format("Can not create a cart. A cart with the given id(%s)" + " already exists", cart.getCartId()));
//        }
//        listOfCarts.put(cart.getCartId(), cart);
        cartRepository.save(cart);

        return cart;
    }

    @Override
    public Optional<Cart> read(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public void update(Long id, Cart cart) {
        if (!listOfCarts.containsKey(cart.getCartId())){
            throw new IllegalArgumentException(String.format("Can not update the cart. The cart with the given id(%s)" + " does not exists", cart.getCartId()));
        }

        listOfCarts.put(id, cart);
    }

    @Override
    public void delete(Long id) {
        if (!listOfCarts.containsKey(id)){
            throw new IllegalArgumentException(String.format("Can not delete the cart. The cart with the given id(%s)" + " does not exists", id));
        }
        listOfCarts.remove(id);
    }
}
