package ru.gabdulindv.senatorshop.controllers.cart;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gabdulindv.senatorshop.model.cart.Cart;
import ru.gabdulindv.senatorshop.model.cart.CartItem;
import ru.gabdulindv.senatorshop.service.CartItemService;
import ru.gabdulindv.senatorshop.service.CartService;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequestMapping("/account/cart")
public class CartController {
    private CartService cartService;
    private CartItemService cartItemService;

    public CartController(CartService cartService, CartItemService cartItemService) {
        this.cartService = cartService;
        this.cartItemService = cartItemService;
    }

    @RequestMapping(value = "/new/item/{cartId}", method = RequestMethod.POST)
    private ResponseEntity addProduct(@PathVariable Long cartId, @RequestBody CartItem cartItem) {
        Optional<Cart> cart = cartService.findById(cartId);
        AtomicBoolean isProductExist = new AtomicBoolean(false);
        cart.ifPresent(value -> {
            cartItem.setCart(value);
            value.getCartItems().forEach(item -> {
                if (item.getProduct().getProductId().equals(cartItem.getProduct().getProductId())) {
                    isProductExist.set(true);
                    int quantity = item.getQuantity() + cartItem.getQuantity();
                    if (quantity > 6) {
                        quantity = 6;
                    }
                    if (quantity < 1) {
                        quantity = 1;
                    }
                    item.setQuantity(quantity);
                }
            });
            if (!isProductExist.get()) {
                value.getCartItems().add(cartItem);
            }
            cartService.saveOrUpdate(value);
        });
        return ResponseEntity.ok("Ok");
    }

    @RequestMapping(value = "/update/{cartId}", method = RequestMethod.POST)
    private ResponseEntity updateCart(@PathVariable Long cartId, @RequestBody Cart cart) {
        cartService.saveOrUpdate(cart);
        return ResponseEntity.ok("Cart was updated");
    }
}
