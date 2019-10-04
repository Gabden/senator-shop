package ru.ryazan.senatorshop.web.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.ryazan.senatorshop.core.domain.Customer;
import ru.ryazan.senatorshop.core.domain.User;
import ru.ryazan.senatorshop.core.domain.address.BillingAddress;
import ru.ryazan.senatorshop.core.domain.address.ShippingAddress;
import ru.ryazan.senatorshop.core.domain.cart.Cart;
import ru.ryazan.senatorshop.core.service.*;

@Controller
public class RegisterController {
    private CustomerService customerService;
    private UserService userService;
    private CartService cartService;
    private BillingAddressService billingAddressService;
    private ShippingAddressService shippingAddressService;
    private PasswordEncoder passwordEncoder;

    public RegisterController(CustomerService customerService, UserService userService, CartService cartService, BillingAddressService billingAddressService, ShippingAddressService shippingAddressService, PasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.userService = userService;
        this.cartService = cartService;
        this.billingAddressService = billingAddressService;
        this.shippingAddressService = shippingAddressService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value = "/register")
    public String register(Model model){
        Customer customer = new Customer();
        BillingAddress billingAddress = new BillingAddress();
        ShippingAddress shippingAddress = new ShippingAddress();
        customer.setBillingAddress(billingAddress);
        customer.setShippingAddress(shippingAddress);
        model.addAttribute("customer", customer);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(@ModelAttribute("customer")Customer customer, Model model){
        User user = new User();
        user.setUsername(customer.getCustomerName());
        user.setEmail(customer.getCustomerEmail());
        user.setPassword(passwordEncoder.encode(customer.getCustomerPassword()));
        user.setRoles("USER");
        user.setActive(1);
        Cart cart = new Cart();
        cart.setCustomer(customer);
        customer.setCart(cart);
        cartService.create(cart);

        customer.setEnabled(true);
        customerService.addCustomer(customer);
        Customer newCustomer = customerService.findCustomerByCustomerNameAndCustomerEmail(customer.getCustomerName(), customer.getCustomerEmail());
        user.setCustomerId(newCustomer.getCustomerId());
        System.out.println("Billing : " + customer.getBillingAddress());
        System.out.println("Shipping : " + customer.getShippingAddress());
        billingAddressService.addBillingAddress(customer.getBillingAddress());
        shippingAddressService.addShippingAddressBilling(customer.getShippingAddress());
        userService.save(user);

        return "registrationSuccess";
    }

}
