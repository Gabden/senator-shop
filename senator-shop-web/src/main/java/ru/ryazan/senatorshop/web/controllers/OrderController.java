package ru.ryazan.senatorshop.web.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ryazan.senatorshop.core.domain.Customer;
import ru.ryazan.senatorshop.core.domain.CustomerOrder;
import ru.ryazan.senatorshop.core.domain.cart.Cart;
import ru.ryazan.senatorshop.core.service.CartService;
import ru.ryazan.senatorshop.core.service.CustomerOrderService;

import java.sql.Timestamp;
import java.util.Optional;

@Controller
@RequestMapping("/order")
public class OrderController {
    private CartService cartService;
    private CustomerOrderService customerOrderService;

    public OrderController(CartService cartService, CustomerOrderService customerOrderService) {
        this.cartService = cartService;
        this.customerOrderService = customerOrderService;
    }

    @RequestMapping("/{cartId}")
    public String order(@PathVariable("cartId") String id, ModelMap modelMap, @AuthenticationPrincipal UserDetails userDetails){
        if (userDetails == null){
            return "redirect:/login";
        }
        CustomerOrder order = new CustomerOrder();
        long ida = Long.parseLong(id);
        Optional<Cart> cart = cartService.read(ida);
        cart.ifPresent(order::setCart);
        Customer customer = cart.get().getCustomer();
        order.setBillingAddress(customer.getBillingAddress());
        order.setShippingAddress(customer.getShippingAddress());
        order.setTimestamp(new Timestamp(System.currentTimeMillis()));
        order.setCustomer(customer);
        customerOrderService.createOrder(order);
        modelMap.addAttribute("cart", cart.get());
        return "redirect:/checkout?cartId=" + id;

    }
}
