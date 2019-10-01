package ru.ryazan.senatorshop.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ryazan.senatorshop.core.domain.cart.Cart;
import ru.ryazan.senatorshop.core.domain.cart.CartItem;
import ru.ryazan.senatorshop.core.service.CartService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping("/cart")
public class CartItemController {
    CartService cartService;

    public CartItemController(CartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping
    public String get (HttpServletRequest request) {
        return "redirect:/cart/" + request.getSession(true).getId();
    }

    @RequestMapping("/{cartId}")
    public String getCart(@PathVariable(value = "cartId") String id, Model model){
        Cart cart = cartService.read(id);
        int grandTotal = 0;
        System.out.println("NO RESST cart dont find: " + (cart == null) + " id: " + id);
        if (cart != null){
            model.addAttribute("cart", cart.getCartItems());
            for (CartItem value : cart.getCartItems().values()) {
                grandTotal += value.getTotalPrice() * value.getQuantity();
            }
        } else {
            model.addAttribute("cart", new HashMap<>());
        }

        model.addAttribute("grandTotal", grandTotal);
        model.addAttribute("cartId", id);
        return "cart";
    }
}
