package ru.ryazan.senatorshop.web.controllers.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ryazan.senatorshop.core.domain.CustomerOrder;
import ru.ryazan.senatorshop.core.domain.cart.CartItem;
import ru.ryazan.senatorshop.core.service.CartItemService;
import ru.ryazan.senatorshop.core.service.CartService;
import ru.ryazan.senatorshop.core.service.CustomerOrderService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class EditCustomerOrder {
    private CustomerOrderService customerOrderService;
    private CartItemService cartItemService;
    private CartService cartService;

    public EditCustomerOrder(CustomerOrderService customerOrderService, CartItemService cartItemService, CartService cartService) {
        this.customerOrderService = customerOrderService;
        this.cartItemService = cartItemService;
        this.cartService = cartService;
    }

    @RequestMapping("/customerOrder/{id}")
    public String editCustomerOrder(@PathVariable("id") Long id, Model model) {
        Optional<CustomerOrder> order = customerOrderService.findById(id);
        order.ifPresent(order1 -> {
            cartService.update(order.get().getCart());

            model.addAttribute("order", order1);
        });
        if (!order.isPresent()) {
            model.addAttribute("order", new ArrayList<>());
        }
        return "admin/edit-customer-order";
    }

    @RequestMapping(value = "/customerOrder/{id}", method = RequestMethod.POST)
    public String refreshOrder(@PathVariable("id") Long id, CustomerOrder updatedCustomerOrder) {
        updatedCustomerOrder.setCustomerOrderId(id);
        customerOrderService.update(updatedCustomerOrder);
        return "redirect:/admin/customerOrder/" + id;
    }

//    @RequestMapping(value = "/customerOrder/delete/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<String> delete(@PathVariable("id")Long id) {
//        customerOrderService.delete(id);
//        return ResponseEntity.ok("ORDER DELETED");
//    }

    @RequestMapping(value = "/customerOrder/delete/item/{id}", method = RequestMethod.DELETE)
    private ResponseEntity<String> deleteCartItem(@PathVariable("id") Long id) {
        cartItemService.deleteById(id);
        return ResponseEntity.ok("ITEM DELETED");
    }

    @RequestMapping(value = "/customerOrder/item/update/{id}", method = RequestMethod.PUT)
    private ResponseEntity<String> updateCartItem(@PathVariable("id") Long id, @RequestParam("q") int quantity) {
        Optional<CartItem> item = cartItemService.findById(id);
        if (item.isPresent()) {
            item.get().setQuantity(quantity);
            cartItemService.update(item.get());

            return ResponseEntity.ok("ITEM QUANTITY UPDATED");
        } else {

            return ResponseEntity.badRequest().body("CartItem not found");
        }
    }

    @RequestMapping(value = "/customerOrder/update/{id}", method = RequestMethod.PUT)
    private ResponseEntity<String> updateOrderStatus(@PathVariable("id") Long id, @RequestParam("status") String status) {
        Optional<CustomerOrder> order = customerOrderService.findById(id);
        if (order.isPresent()) {
            order.get().setStatus(status);
            customerOrderService.update(order.get());
            return ResponseEntity.ok("ORDER STATUS UPDATED");
        } else {
            return ResponseEntity.badRequest().body("ORDER NOT FOUND");
        }

    }


}
