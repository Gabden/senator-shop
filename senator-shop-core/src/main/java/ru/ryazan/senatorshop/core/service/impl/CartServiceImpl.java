package ru.ryazan.senatorshop.core.service.impl;

import org.springframework.stereotype.Service;
import ru.ryazan.senatorshop.core.domain.cart.Cart;
import ru.ryazan.senatorshop.core.service.CartService;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {
    private Map<String, Cart> listOfCarts;

    public CartServiceImpl() {
        listOfCarts = new HashMap<>();
    }

    @Override
    public Cart create(Cart cart) {
        if (listOfCarts.containsKey(cart.getCartId())){
            throw new IllegalArgumentException(String.format("Can not create a cart. A cart with the given id(%s)" + " already exists", cart.getCartId()));
        }
        System.out.println("created cart with id: " + cart.getCartId());
        listOfCarts.put(cart.getCartId(), cart);
        return cart;
    }

    @Override
    public Cart read(String id) {
        return listOfCarts.get(id);
    }

    @Override
    public void update(String id, Cart cart) {
        if (!listOfCarts.containsKey(cart.getCartId())){
            throw new IllegalArgumentException(String.format("Can not update the cart. The cart with the given id(%s)" + " does not exists", cart.getCartId()));
        }

        listOfCarts.put(id, cart);
    }

    @Override
    public void delete(String id) {
        if (!listOfCarts.containsKey(id)){
            throw new IllegalArgumentException(String.format("Can not delete the cart. The cart with the given id(%s)" + " does not exists", id));
        }
        listOfCarts.remove(id);
    }
}
