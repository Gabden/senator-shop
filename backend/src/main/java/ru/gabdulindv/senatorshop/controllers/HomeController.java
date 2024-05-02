package ru.gabdulindv.senatorshop.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.gabdulindv.senatorshop.model.User;
import ru.gabdulindv.senatorshop.model.order.Order;
import ru.gabdulindv.senatorshop.model.order.ReservedCart;
import ru.gabdulindv.senatorshop.model.order.ReservedCartItem;
import ru.gabdulindv.senatorshop.model.product.Product;
import ru.gabdulindv.senatorshop.repository.ProductRepo;
import ru.gabdulindv.senatorshop.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/api/public")
public class HomeController {
    private UserRepository repository;
    private ProductRepo productRepo;

    public HomeController(UserRepository repository, ProductRepo productRepo) {
        this.repository = repository;
        this.productRepo = productRepo;
    }

    @RequestMapping("/hello")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> hello() {
        HashMap<String, String> map = new HashMap<>();
        map.put("all", "Available for all users");
        return map;
    }

    @RequestMapping("/manager/hello")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> manager() {
        HashMap<String, String> map = new HashMap<>();
        map.put("all", "Available for manager users");
        return map;
    }

    @RequestMapping("/admin/hello")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> admin() {
        HashMap<String, String> map = new HashMap<>();
        map.put("all", "Available for admin users");
        return map;
    }

    @RequestMapping("/user")
    public User getUser(Authentication auth, HttpServletRequest request) {
        if (auth == null) {
            return null;
        }
        User user = repository.findUserByUsername(auth.getName());
        user.setToken(request.getHeader("Authorization"));
        return user;
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public User createOrder(Authentication auth, HttpServletRequest request) {
        User user = repository.findUserByUsername(auth.getName());
        Order order = new Order();
        order.setBillingAddress(user.getBillingAddress());
        order.setShippingAddress(user.getShippingAddress());
        order.setTimestamp(new Timestamp(System.currentTimeMillis()));
        order.setStatus("canceled");
        order.setUser(user);

        ReservedCart reservedCart = new ReservedCart();
        reservedCart.setOrder(order);

        Optional<Product> product = productRepo.findById(954L);
        Optional<Product> product2 = productRepo.findById(955L);

        ReservedCartItem reservedCartItem = new ReservedCartItem();
//        reservedCartItem.setCart(reservedCart);
        reservedCartItem.setProduct(product.get());
        reservedCartItem.setQuantity(1);
        reservedCartItem.setCartItemFinalPrice(product.get().getProductPrice());
        reservedCartItem.setCartItemPrice(product.get().getProductPrice());
        reservedCartItem.setTotalPrice(1360);

        ReservedCartItem reservedCartItem2 = new ReservedCartItem();
//        reservedCartItem2.setCart(reservedCart);
        reservedCartItem2.setProduct(product2.get());
        reservedCartItem2.setQuantity(2);
        reservedCartItem2.setCartItemFinalPrice(product2.get().getProductPrice());
        reservedCartItem2.setCartItemPrice(product2.get().getProductPrice());
        reservedCartItem2.setTotalPrice(2910);

        Set<ReservedCartItem> reservedCartItems = new HashSet<>();
        reservedCartItems.add(reservedCartItem);
        reservedCartItems.add(reservedCartItem2);
        reservedCart.setReservedCartItems(reservedCartItems);
        order.setReservedCart(reservedCart);
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        user.setOrderTable(orders);
        repository.save(user);
        return user;
    }


    @RequestMapping(
            value = "/images/logo",
            produces = MediaType.IMAGE_PNG_VALUE
    )
    @ResponseBody
    public byte[] getImageWithMediaType() throws IOException {
        InputStream in = getClass()
                .getResourceAsStream("/ru/gabdulindv/senatorshop/assets/senator-logo.png");
        return IOUtils.toByteArray(in);
    }
}