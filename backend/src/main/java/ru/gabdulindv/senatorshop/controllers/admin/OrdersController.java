package ru.gabdulindv.senatorshop.controllers.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gabdulindv.senatorshop.model.order.Order;
import ru.gabdulindv.senatorshop.service.OrderService;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class OrdersController {
    private OrderService orderService;

    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping("/orders/all")
    public ResponseEntity getOrders(@RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 6, Sort.by("orderId").descending());
        Page<Order> orders = orderService.findAll(pageable);
        return ResponseEntity.ok(orders);
    }

    @RequestMapping("/orders/search")
    public ResponseEntity getOrdersByPhone(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "phone", required = false) String phone, @RequestParam(name = "email", required = false) String email) {
        Pageable pageable = PageRequest.of(page, 6, Sort.by("orderId").descending());
        Page<Order> orders;
        if (phone != null && phone.length() > 0) {
            String plusPhone = "+" + phone.trim();
            orders = orderService.findOrdersByUser_UserDetailsDescription_Phone(plusPhone, pageable);
        } else if (email != null && email.length() > 0) {
            orders = orderService.findOrdersByUser_UsernameContains(email, pageable);
        } else {
            orders = Page.empty();
        }
        return ResponseEntity.ok(orders);
    }

    @RequestMapping("/order/{id}")
    public ResponseEntity findOrderById(@PathVariable("id") Long id) {
        Optional<Order> order = orderService.findById(id);
        if (order.isPresent()) {
            return ResponseEntity.ok(order.get());
        }
        return ResponseEntity.notFound().build();
    }
}
