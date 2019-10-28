package ru.ryazan.senatorshop.web;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.ryazan.senatorshop.core.domain.User;
import ru.ryazan.senatorshop.core.service.*;

@SpringBootApplication(scanBasePackages = {"ru.ryazan.senatorshop.web"
        ,"ru.ryazan.senatorshop.core"})
public class SenatorShopApplication implements ApplicationRunner {
    private UserService userService;
    private CartService cartService;
    private CustomerService customerService;
    private BillingAddressService billingAddressService;
    private ShippingAddressService shippingAddressService;
    private PasswordEncoder passwordEncoder;

    public SenatorShopApplication(UserService userService, PasswordEncoder passwordEncoder, CartService cartService,
                                  CustomerService customerService, BillingAddressService billingAddressService, ShippingAddressService shippingAddressService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.cartService = cartService;
        this.customerService = customerService;
        this.billingAddressService = billingAddressService;
        this.shippingAddressService = shippingAddressService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SenatorShopApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User oldUser = userService.findUserByUsername("admin");
        if (oldUser == null){
            User newAdmin = new User();
            newAdmin.setActive(1);
            newAdmin.setRoles("ADMIN");
            newAdmin.setUsername("admin");
            newAdmin.setPassword(passwordEncoder.encode("123"));
            userService.save(newAdmin);
//            Customer customer = new Customer();
//            customer.setCustomerPassword(passwordEncoder.encode("senator-suhov"));
//            customer.setCustomerPasswordAccept(passwordEncoder.encode("senator-suhov"));
//            customer.setCustomerPhone("89105087799");
//            customer.setCustomerName("admin");
//            User user = new User();
//            user.setUsername("admin");
//            user.setPassword(passwordEncoder.encode("senator-suhov"));
//            user.setRoles("ADMIN");
//            user.setActive(1);
//            Cart cart = new Cart();
//            cart.setCustomer(customer);
//            customer.setCart(cart);
//            cartService.create(cart);
//
//            customer.setEnabled(true);
//            customerService.addCustomer(customer);
//            Customer newCustomer = customerService.findCustomerByCustomerName(customer.getCustomerName());
//            user.setCustomerId(newCustomer.getCustomerId());
//            BillingAddress billingAddress = new BillingAddress();
//            billingAddress.setCountry("Россия");
//            billingAddress.setZipCode("390000");
//            billingAddress.setCity("Рязань");
//            billingAddress.setStreetName("ул.Садовая д.24а");
//            billingAddress.setApartmentNumber("1");
//            ShippingAddress shippingAddress = new ShippingAddress();
//            shippingAddress.setCountry("Россия");
//            shippingAddress.setZipCode("390000");
//            shippingAddress.setCity("Рязань");
//            shippingAddress.setStreetName("ул.Садовая д.24а");
//            shippingAddress.setApartmentNumber("1");
//            customer.setBillingAddress(billingAddress);
//            customer.setShippingAddress(shippingAddress);
//            billingAddressService.addBillingAddress(customer.getBillingAddress());
//            shippingAddressService.addShippingAddressBilling(customer.getShippingAddress());
//            userService.save(user);
        }

    }
}
