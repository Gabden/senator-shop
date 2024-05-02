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
import ru.gabdulindv.senatorshop.model.order.ReservedCart;
import ru.gabdulindv.senatorshop.model.order.ReservedCartItem;
import ru.gabdulindv.senatorshop.model.product.Product;
import ru.gabdulindv.senatorshop.service.OrderService;
import ru.gabdulindv.senatorshop.service.ProductService;
import ru.gabdulindv.senatorshop.service.UserService;
import ru.gabdulindv.senatorshop.service.WebclientService;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/account")
public class AccountController {
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private OrderService orderService;
    private ProductService productService;
    private WebclientService webclientService;

    public AccountController(UserService userService, PasswordEncoder passwordEncoder, OrderService orderService, ProductService productService, WebclientService webclientService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.orderService = orderService;
        this.productService = productService;
        this.webclientService = webclientService;
    }

    @RequestMapping(value = "/update/fio/{id}", method = RequestMethod.POST)
    public ResponseEntity updateFio(@PathVariable Long id, @RequestBody FioModel fioModel) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            user.get().getUserDetailsDescription().setFIOfirst(fioModel.getFiofirst());
            user.get().getUserDetailsDescription().setFIOmiddle(fioModel.getFiomiddle());
            user.get().getUserDetailsDescription().setFIOlast(fioModel.getFiolast());
            user.get().getUserDetailsDescription().setBirthDate(fioModel.getBirthDate());
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
            user.get().setPassword(passwordEncoder.encode(fioModel.getNewPassword()));
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

        return ResponseEntity.ok("Created");
    }

    @RequestMapping(value = "/restore-password", method = RequestMethod.POST)
    public ResponseEntity newPassword(@RequestParam(name = "name") String name) {
        Optional<User> user = userService.findUserByUsernameContains(name);

        if (!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }

        String newPassword = getAlphaNumericString(5);
        user.get().setPassword(passwordEncoder.encode(newPassword));
        userService.save(user.get());
        webclientService.resetPasswordRequest(name,user.get().getUserDetailsDescription().getFIOfirst(), newPassword);
        return ResponseEntity.ok("Password was changed");

    }

    @RequestMapping(value = "/create/order/{id}", method = RequestMethod.POST)
    public ResponseEntity createOrder(@PathVariable("id") Long id, @RequestBody Cart cart) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            Order order = new Order();
            order.setBillingAddress(user.get().getBillingAddress());
            order.setShippingAddress(user.get().getShippingAddress());
            order.setTimestamp(new Timestamp(System.currentTimeMillis()));
            order.setUser(user.get());

            ReservedCart reservedCart = new ReservedCart();
            reservedCart.setOrder(order);

            Set<ReservedCartItem> reservedCartItems = new HashSet<>();

            cart.getCartItems().forEach(cartItem -> {
                ReservedCartItem reservedCartItem = new ReservedCartItem();
                Optional<Product> product = productService.findById(cartItem.getProduct().getProductId());
                product.ifPresent(reservedCartItem::setProduct);
                reservedCartItem.setQuantity(cartItem.getQuantity());
                reservedCartItem.setCartItemFinalPrice(cartItem.getCartItemFinalPrice());
                reservedCartItem.setCartItemPrice(cartItem.getCartItemPrice());
                reservedCartItem.setTotalPrice(cartItem.getTotalPrice());
                reservedCartItem.setCart(reservedCart);
                reservedCartItems.add(reservedCartItem);
                //Update product quantity
                try {
                    int newQuantity = Integer.parseInt(cartItem.getProduct().getProductDetails().getProductUnitInStock()) - cartItem.getQuantity();
                    if (newQuantity < 0) {
                        newQuantity = 0;
                    }
                    if (product.isPresent()) {
                        product.get().getProductDetails().setProductUnitInStock(String.valueOf(newQuantity));
                        product.get().getProductDetails().setOutOfStock(product.get().getProductDetails().getProductUnitInStock().equals("0"));

                        productService.addProduct(product.get());
                    }
                } catch (Exception e) {
                    System.out.println("Error while updating quantity");
                }
                //
            });

            reservedCart.setReservedCartItems(reservedCartItems);
            reservedCart.setGrandTotal(cart.getGrandTotal());
            order.setReservedCart(reservedCart);
            List<Order> orders = user.get().getOrderTable();
            orders.add(order);
            user.get().setOrderTable(orders);
            userService.save(user.get());
            Order createdOrder = user.get().getOrderTable().get(user.get().getOrderTable().size() - 1);

            webclientService.createOrderRequest(user.get().getUsername(), user.get().getUserDetailsDescription().getFIOfirst(), createdOrder);

            return ResponseEntity.ok("Ok");
        }
        return ResponseEntity.badRequest().build();
    }

    private String getAlphaNumericString(int n) {
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
