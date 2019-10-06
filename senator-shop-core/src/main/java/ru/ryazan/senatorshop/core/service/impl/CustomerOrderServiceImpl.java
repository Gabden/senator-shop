package ru.ryazan.senatorshop.core.service.impl;

import org.springframework.stereotype.Service;
import ru.ryazan.senatorshop.core.domain.CustomerOrder;
import ru.ryazan.senatorshop.core.domain.cart.Cart;
import ru.ryazan.senatorshop.core.domain.cart.CartItem;
import ru.ryazan.senatorshop.core.repository.CustomerOrderRepository;
import ru.ryazan.senatorshop.core.service.CartService;
import ru.ryazan.senatorshop.core.service.CustomerOrderService;

import java.util.Optional;
@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {
    private CustomerOrderRepository customerOrderRepository;
    private CartService cartService;

    public CustomerOrderServiceImpl(CustomerOrderRepository customerOrderRepository, CartService cartService) {
        this.customerOrderRepository = customerOrderRepository;
        this.cartService = cartService;
    }

    @Override
    public void createOrder(CustomerOrder customerOrder) {
        customerOrderRepository.save(customerOrder);
    }

    @Override
    public int getGrantTotal(Long cartId) {
        int total = 0;
        Optional<Cart> cart = cartService.read(cartId);
        if (cart.isPresent()){
            for (CartItem item : cart.get().getCartItems()){
                total += item.getTotalPrice();
            }
        }
        return total;
    }
}
