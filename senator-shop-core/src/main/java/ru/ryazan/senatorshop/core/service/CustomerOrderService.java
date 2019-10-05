package ru.ryazan.senatorshop.core.service;

import ru.ryazan.senatorshop.core.domain.CustomerOrder;

public interface CustomerOrderService {
    void createOrder(CustomerOrder customerOrder);
    int getGrantTotal(Long cartId);
}
