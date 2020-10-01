package ru.gabdulindv.senatorshop.controllers.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.gabdulindv.senatorshop.model.User;
import ru.gabdulindv.senatorshop.model.account.FioModel;
import ru.gabdulindv.senatorshop.model.address.BillingAddress;
import ru.gabdulindv.senatorshop.model.address.ShippingAddress;
import ru.gabdulindv.senatorshop.model.cart.Cart;
import ru.gabdulindv.senatorshop.model.order.Order;
import ru.gabdulindv.senatorshop.service.OrderService;
import ru.gabdulindv.senatorshop.service.UserService;
import ru.gabdulindv.senatorshop.service.mail.EmailService;

import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private OrderService orderService;
    private EmailService emailService;

    public AccountController(UserService userService, PasswordEncoder passwordEncoder, OrderService orderService, EmailService emailService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.orderService = orderService;
        this.emailService = emailService;
    }

    @RequestMapping(value = "/update/fio/{id}", method = RequestMethod.POST)
    public ResponseEntity updateFio(@PathVariable Long id, @RequestBody FioModel fioModel) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            user.get().getUserDetailsDescription().setFIOfirst(fioModel.getFiofirst());
            user.get().getUserDetailsDescription().setFIOmiddle(fioModel.getFiomiddle());
            user.get().getUserDetailsDescription().setFIOlast(fioModel.getFiolast());
            userService.save(user.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/update/phone/{id}", method = RequestMethod.POST)
    public ResponseEntity updatePhone(@PathVariable Long id, @RequestBody FioModel fioModel) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            user.get().getUserDetailsDescription().setPhone(fioModel.getPhone());
            userService.save(user.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/update/username/{id}", method = RequestMethod.POST)
    public ResponseEntity updateUsername(@PathVariable Long id, @RequestBody FioModel fioModel) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            user.get().setUsername(fioModel.getUsername());
            userService.save(user.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/update/password/{id}", method = RequestMethod.POST)
    public ResponseEntity updatePassword(@PathVariable Long id, @RequestBody FioModel fioModel) {
        Optional<User> user = userService.findById(id);

        if (user.isPresent()) {
            String oldPass = user.get().getPassword();
            String oldPassFromUser = fioModel.getOldPassword();
            if (!passwordEncoder.matches(oldPassFromUser, oldPass)) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Старый пароль не совпадает с текущим");
            }
            user.get().setPassword(passwordEncoder.encode(oldPassFromUser));
            userService.save(user.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping("/orders/all")
    public ResponseEntity getOrdersByPhone(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "email", required = false) String email) {
        Pageable pageable = PageRequest.of(page, 6, Sort.by("orderId").descending());
        Page<Order> orders = orderService.findOrdersByUser_UsernameContains(email, pageable);
        return ResponseEntity.ok(orders);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity createAccount(@RequestBody User user) {
        Optional<User> oldUser = userService.findUserByUsernameContains(user.getUsername());
        if (oldUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        user.setRoles("USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(1);
        BillingAddress billingAddress = new BillingAddress();
        billingAddress.setCountry("Россия");
        billingAddress.setCity("Рязань");
        billingAddress.setZipCode("390000");
        billingAddress.setStreetName("Свободы");
        billingAddress.setApartmentNumber("24a");

        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setCountry("Россия");
        shippingAddress.setCity("Рязань");
        shippingAddress.setZipCode("390000");
        shippingAddress.setStreetName("Свободы");
        shippingAddress.setApartmentNumber("24a");

        Cart cart = new Cart();
        user.getUserDetailsDescription().setUser(user);

        user.setCart(cart);
        user.setBillingAddress(billingAddress);
        user.setShippingAddress(shippingAddress);

        userService.save(user);

        emailService.sendRegistrationEmail(user.getUsername());

        return ResponseEntity.ok("Created");
    }
}
