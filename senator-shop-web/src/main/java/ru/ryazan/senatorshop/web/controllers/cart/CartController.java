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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @RequestMapping("/ajax")
    public Cart read(HttpServletRequest request, @AuthenticationPrincipal UserDetails userDetails){
        String sessionId = String.valueOf(request.getSession().getAttribute("USERSESSION"));
        Optional<Cart> cartFromSessionId = cartService.readBySessionId(sessionId);
        if (userDetails == null){
            Optional<Cart> cart = cartService.readBySessionId(sessionId);
            if (cart.isPresent()){
               return cart.get();
            } else {
                Cart cartNew = new Cart();
                cartNew.setCartItems(new ArrayList<>());
                cartNew.setSessionId(sessionId);
                cartService.create(cartNew);
                return cartNew;
            }
        }else {
            Cart cartFromAuthUser = customerService.findCustomerByCustomerName(userDetails.getUsername()).getCart();

            List<CartItem> summaryListItems = new ArrayList<>(cartFromAuthUser.getCartItems());
            if (cartFromSessionId.isPresent()){
                if (cartFromSessionId.get().getCartItems().size() > 0){
                   OUTTER: for (CartItem item: cartFromSessionId.get().getCartItems()){
                        for (CartItem userItem : summaryListItems){
                            if (item.getProduct().getId().equals(userItem.getProduct().getId())){
                                userItem.setQuantity(userItem.getQuantity() + item.getQuantity());
                                cartItemService.update(userItem);
                                continue OUTTER;
                            }
                        }
                            item.setCart(cartFromAuthUser);
                            cartItemService.addItem(item);
                    }

                }
            }

            return cartFromAuthUser;
        }
    }

    @RequestMapping("bySession/{sessionId}")
    public Cart readBySessionId(@PathVariable(value = "sessionId")String sessionId){
        Optional<Cart> cart = cartService.readBySessionId(sessionId);
        if (!cart.isPresent()){
            Cart newCart = new Cart();
            newCart.setSessionId(sessionId);
            return newCart;
        }else {
            return cart.get();
        }
    }

    @RequestMapping(value = "/refreshQuantity/{productId}", method = RequestMethod.PUT)
    public void refreshQuantity(@PathVariable(value = "productId") Long productId, @RequestParam(name = "quantity") int quantity, @AuthenticationPrincipal UserDetails userDetails, HttpServletRequest request){
        if (userDetails != null){
            Customer customer = customerService.findCustomerByCustomerName(userDetails.getUsername());
            Cart cart = customer.getCart();
            List<CartItem> cartItems =  cart.getCartItems();
            for (CartItem item: cartItems){
                if (item.getProduct().getId().equals(productId)){
                    item.setQuantity(quantity);
                    cartItemService.update(item);
                    return;
                }
            }
        } else {
            String sessionId = String.valueOf(request.getSession().getAttribute("USERSESSION"));
            Optional<Cart> cart = cartService.readBySessionId(sessionId);
            List<CartItem> cartItems =  cart.get().getCartItems();
            for (CartItem item: cartItems){
                if (item.getProduct().getId().equals(productId)){
                    item.setQuantity(quantity);
                    cartItemService.update(item);
                    return;
                }
            }
        }
    }

    @RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addItem(@PathVariable(value = "productId") Long productId, @RequestParam(name = "quantity") int quantity, @AuthenticationPrincipal UserDetails userDetails, HttpServletRequest request){
        Optional<Product> product = productService.findById(productId);
        if (userDetails != null){
            Customer customer = customerService.findCustomerByCustomerName(userDetails.getUsername());
            Cart cart = customer.getCart();
            List<CartItem> cartItems =  cart.getCartItems();
            for (CartItem item: cartItems){
                if (item.getProduct().getId().equals(productId)){
                    item.setQuantity(quantity);
                    cartItemService.addItem(item);
                    return;
                }
            }
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product.get());
            cartItem.setQuantity(quantity);
            cartItem.setCart(cart);
            cartItemService.addItem(cartItem);
        } else {
            String sessionId = String.valueOf(request.getSession().getAttribute("USERSESSION"));
            Optional<Cart> cart = cartService.readBySessionId(sessionId);
            List<CartItem> cartItems =  cart.get().getCartItems();
            for (CartItem item: cartItems){
                if (item.getProduct().getId().equals(productId)){
                    item.setQuantity(item.getQuantity() + quantity);
                    cartItemService.addItem(item);
                    return;
                }
            }
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product.get());
            cartItem.setQuantity(quantity);
            cartItem.setCart(cart.get());
            cartItemService.addItem(cartItem);

        }

    }

    @RequestMapping(value = "/delete/{productId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeItem(@PathVariable(value = "productId") Long productId, @AuthenticationPrincipal UserDetails userDetails, HttpServletRequest request){
        Cart cart = null;
        String sessionId = String.valueOf(request.getSession().getAttribute("USERSESSION"));
        if (userDetails != null){
            Customer customer = customerService.findCustomerByCustomerName(userDetails.getUsername());
            cart = customer.getCart();
        } else {
            cart = cartService.readBySessionId(sessionId).get();
        }
        List<CartItem> cartItems =  cart.getCartItems();
        for (CartItem item: cartItems){
            if (item.getProduct().getId().equals(productId)){
                cartItemService.deleteById(item.getCartItemId());

            }
        }

    }

    @RequestMapping(value = "/ajax/clearCart", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeAllItems(@AuthenticationPrincipal UserDetails userDetails, HttpServletRequest request){
        String sessionId = String.valueOf(request.getSession().getAttribute("USERSESSION"));
        if (userDetails != null){
            Customer customer = customerService.findCustomerByCustomerName(userDetails.getUsername());
            Cart cart = customer.getCart();
            cartItemService.deleteAll(cart);
        } else {
            Optional<Cart> cart = cartService.readBySessionId(sessionId);
            cart.ifPresent(value -> cartItemService.deleteAll(value));
        }
    }

    @RequestMapping(value = "/ajax/clearCartTemp", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeAllTempCart(@AuthenticationPrincipal UserDetails userDetails, HttpServletRequest request) {
        List<Cart> cartsTemp = cartService.findAllBySessionIdIsNotNull();
        if (cartsTemp != null && cartsTemp.size() > 0) {
            cartsTemp.forEach(cart -> cartService.delete(cart.getCartId()));
        }
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
