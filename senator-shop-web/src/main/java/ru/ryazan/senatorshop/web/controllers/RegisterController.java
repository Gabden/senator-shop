package ru.ryazan.senatorshop.web.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.ryazan.senatorshop.core.domain.Customer;
import ru.ryazan.senatorshop.core.domain.User;
import ru.ryazan.senatorshop.core.domain.address.BillingAddress;
import ru.ryazan.senatorshop.core.domain.address.ShippingAddress;
import ru.ryazan.senatorshop.core.domain.cart.Cart;
import ru.ryazan.senatorshop.core.service.*;

import javax.validation.Valid;
import java.util.List;

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

        model.addAttribute("customer", customer);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(@Valid @ModelAttribute("customer")Customer customer, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            System.out.println("BINDING RESULT ERROR");
            return "register";
        }
        if (!customer.getCustomerPassword().equals(customer.getCustomerPasswordAccept())){
            model.addAttribute("nameMsg", "Пароли не совпадают");
            return "register";
        }
        List<Customer> customers = customerService.getAllCustomers();
        for (Customer csmr : customers){
            if (customer.getCustomerName().equals(csmr.getCustomerName())){
                model.addAttribute("nameMsg", "Пользователь с такой почтой существует");
                return "register";
            }
        }
        User user = new User();
        user.setUsername(customer.getCustomerName());
        user.setPassword(passwordEncoder.encode(customer.getCustomerPassword()));
        user.setRoles("USER");
        user.setActive(1);
        Cart cart = new Cart();
        cart.setCustomer(customer);
        customer.setCart(cart);
        cartService.create(cart);

        customer.setEnabled(true);
        customerService.addCustomer(customer);
        Customer newCustomer = customerService.findCustomerByCustomerName(customer.getCustomerName());
        user.setCustomerId(newCustomer.getCustomerId());
        BillingAddress billingAddress = new BillingAddress();
        billingAddress.setCountry("Россия");
        billingAddress.setZipCode("390000");
        billingAddress.setCity("Рязань");
        billingAddress.setStreetName("ул.Садовая д.24а");
        billingAddress.setApartmentNumber("1");
        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setCountry("Россия");
        shippingAddress.setZipCode("390000");
        shippingAddress.setCity("Рязань");
        shippingAddress.setStreetName("ул.Садовая д.24а");
        shippingAddress.setApartmentNumber("1");
        customer.setBillingAddress(billingAddress);
        customer.setShippingAddress(shippingAddress);
        billingAddressService.addBillingAddress(customer.getBillingAddress());
        shippingAddressService.addShippingAddressBilling(customer.getShippingAddress());
        userService.save(user);

        return "registrationSuccess";
    }

}
