package ru.ryazan.senatorshop.web.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ryazan.senatorshop.core.domain.Customer;
import ru.ryazan.senatorshop.core.domain.CustomerOrder;
import ru.ryazan.senatorshop.core.domain.User;
import ru.ryazan.senatorshop.core.domain.address.BillingAddress;
import ru.ryazan.senatorshop.core.domain.address.ShippingAddress;
import ru.ryazan.senatorshop.core.domain.cart.Cart;
import ru.ryazan.senatorshop.core.mail.EmailService;
import ru.ryazan.senatorshop.core.service.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class RegisterController {
    private CustomerService customerService;
    private UserService userService;
    private CartService cartService;
    private BillingAddressService billingAddressService;
    private ShippingAddressService shippingAddressService;
    private PasswordEncoder passwordEncoder;
    private CustomerOrderService customerOrderService;
    private EmailService emailService;

    public RegisterController(CustomerOrderService customerOrderService, CustomerService customerService
            , UserService userService, CartService cartService, BillingAddressService billingAddressService
            , ShippingAddressService shippingAddressService, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.customerService = customerService;
        this.userService = userService;
        this.cartService = cartService;
        this.billingAddressService = billingAddressService;
        this.shippingAddressService = shippingAddressService;
        this.passwordEncoder = passwordEncoder;
        this.customerOrderService = customerOrderService;
        this.emailService = emailService;
    }

    @RequestMapping("/profile")
    public String startProfile(){
        return "redirect:/user-profile?page=0";
    }
    @RequestMapping("/user-profile")
    public String profile(@AuthenticationPrincipal UserDetails userDetails, Model model, @RequestParam(name = "page") int page){
        if (userDetails == null){
            return "home";
        } else {

            Customer customer = customerService.findCustomerByCustomerName(userDetails.getUsername());

            Pageable firstPageWithTwoElements = PageRequest.of(page, 5, Sort.by("customerOrderId").descending());
            Page<CustomerOrder> orderByCustomer = customerOrderService.findAllByCustomer(customer, firstPageWithTwoElements);

            int totalPages = orderByCustomer.getTotalPages();

            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }

            model.addAttribute("customer", customer);
            model.addAttribute("orders", orderByCustomer);
            model.addAttribute("url", "/user-profile");
            return "profile";
        }
    }
    @RequestMapping(value = "/updateProfile" , method = RequestMethod.POST)
    public String updateProfile(@ModelAttribute(name = "customer") Customer customer, @AuthenticationPrincipal UserDetails userDetails){
        Optional<Customer> oldCustomer = customerService.getCustomerById(customer.getCustomerId());
        if (oldCustomer.isPresent()){
            oldCustomer.get().setCustomerName(customer.getCustomerName());
            oldCustomer.get().setCustomerPhone(customer.getCustomerPhone());
            oldCustomer.get().setFIOfirst(customer.getFIOfirst());
            oldCustomer.get().setFIOlast(customer.getFIOlast());
            oldCustomer.get().setFIOmiddle(customer.getFIOmiddle());
            oldCustomer.get().setCustomerPassword(passwordEncoder.encode(customer.getCustomerPassword()));
            oldCustomer.get().setCustomerPasswordAccept(passwordEncoder.encode(customer.getCustomerPasswordAccept()));
            if (!customer.getCustomerName().equals(userDetails.getUsername())){
                User user = userService.findUserByUsername(userDetails.getUsername());
                user.setUsername(customer.getCustomerName());
                userService.save(user);
                customerService.addCustomer(oldCustomer.get());
                return "redirect:/logout";
            } else {
                customerService.addCustomer(oldCustomer.get());
            }
        }
        return "redirect:/logout";
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
        Pageable pageable = PageRequest.of(0, 10000, Sort.by("customerId").descending());
        Page<Customer> customers = customerService.getAllCustomers(pageable);
        for (Customer csmr : customers){
            if (customer.getCustomerName().equals(csmr.getCustomerName())){
                model.addAttribute("nameMsg", "Пользователь с такой почтой существует");
                return "register";
            }
        }
        if (customer.getFIOfirst() == null){
            customer.setFIOfirst("noname");
        }
        if (customer.getFIOmiddle() == null){
            customer.setFIOmiddle("noname");
        }
        if (customer.getFIOlast() == null){
            customer.setFIOlast("noname");
        }
        createUser(customer, "USER");
        emailService.sendRegistrationEmail(customer.getCustomerName());

        return "registrationSuccess";
    }

    @RequestMapping("/create-admin")
    public String createAdmin(Model model){
        User oldUser = userService.findUserByUsername("admin");
        if (oldUser == null){
            Customer customer = new Customer();
            customer.setCustomerName("senator");
            customer.setCustomerPassword("senator-suhov24!");
            customer.setCustomerPhone("8991050778");
            createUser(customer, "ADMIN");
            model.addAttribute("msg", "Учетная запись администратора создана успешно");
            return "admin-create";
        }
        model.addAttribute("msg","Учетная запись администратора уже существует");
        return "admin-create";
    }
    private void createUser(Customer customer, String role){
        User user = new User();
        user.setUsername(customer.getCustomerName());
        user.setPassword(passwordEncoder.encode(customer.getCustomerPassword()));
        user.setRoles(role);
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
        billingAddress.setZipCode("390006");
        billingAddress.setCity("Рязань");
        billingAddress.setStreetName("ул.Свободы д.24а");
        billingAddress.setApartmentNumber("1");
        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setCountry("Россия");
        shippingAddress.setZipCode("390006");
        shippingAddress.setCity("Рязань");
        shippingAddress.setStreetName("ул.Свободы д.24а");
        shippingAddress.setApartmentNumber("1");
        customer.setBillingAddress(billingAddress);
        customer.setShippingAddress(shippingAddress);
        billingAddressService.addBillingAddress(customer.getBillingAddress());
        shippingAddressService.addShippingAddressBilling(customer.getShippingAddress());
        userService.save(user);
    }

}
