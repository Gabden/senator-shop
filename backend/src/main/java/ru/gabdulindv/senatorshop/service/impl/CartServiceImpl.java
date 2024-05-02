package ru.gabdulindv.senatorshop.service.impl;

import org.springframework.stereotype.Service;
import ru.gabdulindv.senatorshop.model.cart.Cart;
import ru.gabdulindv.senatorshop.model.cart.CartItem;
import ru.gabdulindv.senatorshop.model.product.Product;
import ru.gabdulindv.senatorshop.repository.CartRepo;
import ru.gabdulindv.senatorshop.service.CartService;
import ru.gabdulindv.senatorshop.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private CartRepo cartRepo;
    private ProductService productService;

    public CartServiceImpl(CartRepo cartRepo, ProductService productService) {
        this.cartRepo = cartRepo;
        this.productService = productService;
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
        List<CartItem> updatedCartItems = new ArrayList<>();
        for (CartItem cartItem : cart.getCartItems()) {
            Optional<Product> product = productService.findById(cartItem.getProduct().getProductId());
            product.ifPresent(cartItem::setProduct);
            updatedCartItems.add(cartItem);
        }
        cart.setCartItems(updatedCartItems);
        cartRepo.save(cart);
    }
}
