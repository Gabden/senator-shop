package ru.ryazan.senatorshop.web.controllers.cart;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ryazan.senatorshop.core.domain.Customer;
import ru.ryazan.senatorshop.core.domain.cart.Cart;
import ru.ryazan.senatorshop.core.domain.cart.CartItem;
import ru.ryazan.senatorshop.core.service.CartService;
import ru.ryazan.senatorshop.core.service.CustomerService;

import java.util.Optional;

@Controller
@RequestMapping("/customer/cart")
public class CustomerCartController {

    private CustomerService customerService;
    private CartService cartService;

    public CustomerCartController(CustomerService customerService, CartService cartService) {
        this.customerService = customerService;
        this.cartService = cartService;
    }

    @RequestMapping
    public String getCustomerCart ( @AuthenticationPrincipal UserDetails userDetails){
        Customer customer = customerService.findCustomerByCustomerName(userDetails.getUsername());
        long cartId = customer.getCart().getCartId();
        return "redirect:/customer/cart/" + cartId;
    }

    @RequestMapping("/{cartId}")
    public String getCartRedirect(@PathVariable("cartId") Long id, Model model){
        Optional<Cart> cart = cartService.read(id);
        int grandTotal = 0;

        cart.get();
        model.addAttribute("cart", cart.get().getCartItems());
        for (CartItem value : cart.get().getCartItems()) {
            grandTotal += value.getTotalPrice();
        }

        model.addAttribute("grandTotal", grandTotal);
        model.addAttribute("cartId", id);
        return "cart";
    }
}
