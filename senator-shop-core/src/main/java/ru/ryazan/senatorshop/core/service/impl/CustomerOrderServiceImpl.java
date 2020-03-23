package ru.ryazan.senatorshop.core.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.ryazan.senatorshop.core.domain.Customer;
import ru.ryazan.senatorshop.core.domain.CustomerOrder;
import ru.ryazan.senatorshop.core.domain.cart.Cart;
import ru.ryazan.senatorshop.core.domain.cart.CartItem;
import ru.ryazan.senatorshop.core.mail.EmailService;
import ru.ryazan.senatorshop.core.repository.CustomerOrderRepository;
import ru.ryazan.senatorshop.core.service.CartService;
import ru.ryazan.senatorshop.core.service.CustomerOrderService;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {
    private CustomerOrderRepository customerOrderRepository;
    private CartService cartService;
    private EmailService mailSender;

    public CustomerOrderServiceImpl(CustomerOrderRepository customerOrderRepository, CartService cartService, EmailService mailSender) {
        this.customerOrderRepository = customerOrderRepository;
        this.cartService = cartService;
        this.mailSender = mailSender;
    }

    @Override
    public void createOrder(CustomerOrder customerOrder) {
        customerOrderRepository.save(customerOrder);
    }

    @Override
    public int getGrantTotal(Long cartId) {
        int total = 0;
        Optional<Cart> cart = cartService.read(cartId);
        if (cart.isPresent()) {
            for (CartItem item : cart.get().getCartItems()) {
                total += item.getTotalPrice();
            }
        }
        return total;
    }

    @Override
    public Page<CustomerOrder> findAllByCustomer(Customer customer, Pageable pageable) {
        return customerOrderRepository.findAllByCustomer(customer, pageable);
    }

    @Override
    public Page<CustomerOrder> findAll(Pageable pageable) {
        return customerOrderRepository.findAll(pageable);
    }

    @Override
    public void sendEmailToAdmin(Cart cart) {
        System.out.println("Sending Email...");

        List<CustomerOrder> orders = customerOrderRepository.findCustomerOrderByCart(cart);
        Long orderId = orders.get(orders.size() - 1).getCustomerOrderId();
        try {
            mailSender.sendOrderEmail(cart, orderId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }

    @Override
    public Optional<CustomerOrder> findById(long id) {
        return customerOrderRepository.findById(id);
    }

    @Override
    public void update(CustomerOrder updatedCustomerOrder) {
        customerOrderRepository.save(updatedCustomerOrder);
    }

    @Override
    public void delete(Long id) {
        customerOrderRepository.deleteById(id);
    }

    @Override
    public List<CustomerOrder> findCustomerOrdersByStatus(String created) {
        return customerOrderRepository.findCustomerOrdersByStatus(created);
    }
}
