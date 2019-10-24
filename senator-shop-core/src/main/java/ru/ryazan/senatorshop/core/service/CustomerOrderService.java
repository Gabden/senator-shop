package ru.ryazan.senatorshop.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.ryazan.senatorshop.core.domain.Customer;
import ru.ryazan.senatorshop.core.domain.CustomerOrder;

public interface CustomerOrderService {
    void createOrder(CustomerOrder customerOrder);
    int getGrantTotal(Long cartId);
    Page<CustomerOrder> findAllByCustomer(Customer customer, Pageable pageable);
    Page<CustomerOrder> findAll(Pageable pageable);
}
