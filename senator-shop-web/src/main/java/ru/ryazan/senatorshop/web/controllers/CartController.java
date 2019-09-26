package ru.ryazan.senatorshop.web.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ryazan.senatorshop.core.domain.cart.Cart;
import ru.ryazan.senatorshop.core.service.CartService;
import ru.ryazan.senatorshop.core.service.ProductService;

@RestController
@RequestMapping("/rest/cart")
public class CartController {
    private CartService cartService;
    private ProductService productService;

    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @RequestMapping("/{cartId}")
    public Cart read(@PathVariable(value = "cartId")String cartId){
        return cartService.read(cartId);
    }

}
