package ru.gabdulindv.senatorshop.controllers.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gabdulindv.senatorshop.model.order.Order;
import ru.gabdulindv.senatorshop.service.OrderService;
import ru.gabdulindv.senatorshop.service.ReservedCartItemService;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class OrdersController {
    private OrderService orderService;
    private ReservedCartItemService reservedCartItemService;

    public OrdersController(OrderService orderService, ReservedCartItemService reservedCartItemService) {
        this.orderService = orderService;
        this.reservedCartItemService = reservedCartItemService;
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

    @RequestMapping("/order/update/status/{id}")
    public ResponseEntity updateOrderStatus(@PathVariable("id") Long id, @RequestParam("status") String status) {
        Optional<Order> order = orderService.findById(id);
        if (order.isPresent()) {
            order.get().setStatus(status);
            orderService.saveOrUpdate(order.get());
            return ResponseEntity.ok("Status has been updated");
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/order/delete/{id}", method = RequestMethod.POST)
    public ResponseEntity deleteOrderById(@PathVariable("id") Long id) {
        orderService.deleteById(id);
        return ResponseEntity.ok("Order deleted");
    }

    @RequestMapping(value = "/order/cartItem/delete/{id}", method = RequestMethod.POST)
    public ResponseEntity deleteItemById(@PathVariable("id") Long id) {
        reservedCartItemService.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
}
