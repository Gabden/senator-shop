package ru.ryazan.senatorshop.web.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.ryazan.senatorshop.core.domain.Product;
import ru.ryazan.senatorshop.core.domain.cart.Cart;
import ru.ryazan.senatorshop.core.domain.cart.CartItem;
import ru.ryazan.senatorshop.core.service.CartService;
import ru.ryazan.senatorshop.core.service.ProductService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restCart/cart")
@CrossOrigin
public class CartController {
    private CartService cartService;
    private ProductService productService;

    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @RequestMapping("/{cartId}")
    public Cart read(@PathVariable(value = "cartId")String cartId){
        Cart read = cartService.read(cartId);
        System.out.println("cart dont find: " + (read == null) + " id: " + cartId);
        return read;
    }

    @RequestMapping(value = "/{cartId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@PathVariable(value = "cartId") String cartId, @RequestBody Cart cart){
        cartService.update(cartId, cart);
    }

    @RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "cartId") String cartId){
        cartService.delete(cartId);
    }

    @RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addItem(@PathVariable(value = "productId") Long productId, HttpServletRequest request){
        String sessionId = request.getSession(true).getId();
        System.out.println(sessionId);
        Cart cart = cartService.read(sessionId);
        if (cart == null){
            cart = new Cart(sessionId);
            cartService.create(cart);
        }
        Optional<Product> product = productService.findById(productId);
        if (product.get() == null){
            throw new IllegalArgumentException(new Exception());
        }
        cart.addCartItem(new CartItem(product.get()));
        cartService.update(sessionId, cart);

    }

    @RequestMapping(value = "/add/{productId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeItem(@PathVariable(value = "productId") Long productId, HttpServletRequest request){
        String sessionId = request.getSession(true).getId();
        Cart cart = cartService.read(sessionId);
        if (cart == null){
            cart = new Cart(sessionId);

        }
        Optional<Product> product = productService.findById(productId);
        if (product.get() == null){
            throw new IllegalArgumentException(new Exception());
        }
        cart.removeCartItem(new CartItem(product.get()));
        cartService.update(sessionId, cart);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Illegal request, please verify your payload")
    public void handleClientError(Exception e){}

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal server error")
    public void handleServerError(Exception e){}



}
