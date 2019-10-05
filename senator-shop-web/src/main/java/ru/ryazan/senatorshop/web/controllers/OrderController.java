package ru.ryazan.senatorshop.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ryazan.senatorshop.core.domain.Customer;
import ru.ryazan.senatorshop.core.domain.CustomerOrder;
import ru.ryazan.senatorshop.core.domain.cart.Cart;
import ru.ryazan.senatorshop.core.service.CartService;

import java.util.Optional;

@Controller
@RequestMapping("/order")
public class OrderController {
    private CartService cartService;

    public OrderController(CartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping("/{cartId")
    public String order(@PathVariable("cartId") Long id){
        CustomerOrder order = new CustomerOrder();
        Optional<Cart> cart = cartService.read(id);
        cart.ifPresent(order::setCart);
        Customer customer = cart.get().getCustomer();
        order.setBillingAddress(customer.getBillingAddress());
        order.setShippingAddress(customer.getShippingAddress());
        customerOrderService.save(order);
        return "redirect:/checkout?cartId=" + id;

    }
}
