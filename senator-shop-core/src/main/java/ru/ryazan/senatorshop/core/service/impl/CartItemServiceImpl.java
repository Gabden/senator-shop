package ru.ryazan.senatorshop.core.service.impl;

import org.springframework.stereotype.Service;
import ru.ryazan.senatorshop.core.domain.Customer;
import ru.ryazan.senatorshop.core.domain.cart.Cart;
import ru.ryazan.senatorshop.core.domain.cart.CartItem;
import ru.ryazan.senatorshop.core.repository.CartItemRepository;
import ru.ryazan.senatorshop.core.service.CartItemService;
import ru.ryazan.senatorshop.core.service.CartService;
import ru.ryazan.senatorshop.core.service.CustomerService;

import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    private CartItemRepository cartItemRepository;
    private CartService cartService;
    private CustomerService customerService;

    public CartItemServiceImpl(CartItemRepository cartItemRepository, CartService cartService, CustomerService customerService) {
        this.cartItemRepository = cartItemRepository;
        this.cartService = cartService;
        this.customerService = customerService;
    }



    @Override
    public Optional<CartItem> findById(Long id) {
        return cartItemRepository.findById(id);
    }

    @Override
    public void update(CartItem item) {
        cartItemRepository.save(item);
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
    public void deleteAllInOrder(Long cartId) {
        Optional<Cart> cart = cartService.read(cartId);
        cart.ifPresent(cart1 -> {for(CartItem item: cart1.getCartItems()){
            deleteById(item.getCartItemId());
        }});
    }
    @Override
    public void deleteAll(Cart cart) {
        for(CartItem item: cart.getCartItems()){
            deleteById(item.getCartItemId());
        }
    }

    @Override
    public boolean replaceCarts(Cart cart){
        Customer customer = cart.getCustomer();
        customer.setCustomerPasswordAccept(customer.getCustomerPassword());
        Cart newCart = new Cart();
        newCart.setCustomer(customer);
        customer.setCart(newCart);
        customerService.addCustomer(customer);
        cartService.create(cart);
        return true;
    }


    @Override
    public void deleteCartItemByProductId(Long id) {
        cartItemRepository.deleteCartItemByProductId(id);
    }
}
