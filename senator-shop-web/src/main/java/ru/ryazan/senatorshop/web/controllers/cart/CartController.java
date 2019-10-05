package ru.ryazan.senatorshop.web.controllers.cart;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.ryazan.senatorshop.core.domain.Customer;
import ru.ryazan.senatorshop.core.domain.Product;
import ru.ryazan.senatorshop.core.domain.cart.Cart;
import ru.ryazan.senatorshop.core.domain.cart.CartItem;
import ru.ryazan.senatorshop.core.service.CartItemService;
import ru.ryazan.senatorshop.core.service.CartService;
import ru.ryazan.senatorshop.core.service.CustomerService;
import ru.ryazan.senatorshop.core.service.ProductService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.*;

@RestController
@RequestMapping("/restCart/cart")
@CrossOrigin
public class CartController {
    private CartService cartService;
    private ProductService productService;
    private CustomerService customerService;
    private CartItemService cartItemService;

    public CartController(CartService cartService, ProductService productService, CustomerService customerService, CartItemService cartItemService) {
        this.cartService = cartService;
        this.productService = productService;
        this.customerService = customerService;
        this.cartItemService = cartItemService;
    }

    @RequestMapping("/{cartId}")
    public Cart read(@PathVariable(value = "cartId")Long cartId){
        return cartService.read(cartId).get();
    }



    @RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addItem(@PathVariable(value = "productId") Long productId, @AuthenticationPrincipal UserDetails userDetails){
        Customer customer = customerService.findCustomerByCustomerName(userDetails.getUsername());
        Cart cart = customer.getCart();
        Optional<Product> product = productService.findById(productId);
        List<CartItem> cartItems =  cart.getCartItems();
        for (CartItem item: cartItems){
            if (item.getProduct().getId().equals(productId)){
                item.setQuantity(item.getQuantity() + 1);
                cartItemService.addItem(item);
                return;
            }
        }
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product.get());
        cartItem.setQuantity(1);
        cartItem.setCart(cart);
        cartItemService.addItem(cartItem);

    }

    @RequestMapping(value = "/delete/{productId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeItem(@PathVariable(value = "productId") Long productId, @AuthenticationPrincipal UserDetails userDetails){
        Customer customer = customerService.findCustomerByCustomerName(userDetails.getUsername());
        Cart cart = customer.getCart();
        List<CartItem> cartItems =  cart.getCartItems();
        for (CartItem item: cartItems){
            if (item.getProduct().getId().equals(productId)){
                cartItemService.deleteById(item.getCartItemId());

            }
        }

    }

    @RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeAllItems(@PathVariable(value = "cartId") Long cartId){
       Optional<Cart> cart = cartService.read(cartId);
        cart.ifPresent(value -> cartItemService.deleteAll(value));
    }
//    @RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    public void addItem(@PathVariable(value = "productId") Long productId, @AuthenticationPrincipal UserDetails userDetails, HttpServletRequest request){
//        String sessionId = request.getSession(true).getId();
//        Cart cart = cartService.read(sessionId);
//        if (cart == null){
//            cart = new Cart(sessionId);
//            cartService.create(cart);
//        }
//        Optional<Product> product = productService.findById(productId);
//        if (product.get() == null){
//            throw new IllegalArgumentException(new Exception());
//        }
//        cart.addCartItem(new CartItem(product.get()));
//        cartService.update(sessionId, cart);
//
//    }
//
//    @RequestMapping(value = "/delete/{productId}", method = RequestMethod.DELETE)
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    public void removeItem(@PathVariable(value = "productId") Long productId, HttpServletRequest request){
//        String sessionId = request.getSession(true).getId();
//        Cart cart = cartService.read(sessionId);
//        if (cart == null){
//            cart = new Cart(sessionId);
//
//        }
//        Optional<Product> product = productService.findById(productId);
//        if (product.get() == null){
//            throw new IllegalArgumentException(new Exception());
//        }
//        cart.removeCartItem(new CartItem(product.get()));
//        cartService.update(sessionId, cart);
//    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Illegal request, please verify your payload")
    public void handleClientError(Exception e){}

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal server error")
    public void handleServerError(Exception e){}



}
