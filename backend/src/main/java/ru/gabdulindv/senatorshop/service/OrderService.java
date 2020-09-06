package ru.gabdulindv.senatorshop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.gabdulindv.senatorshop.model.order.Order;

public interface OrderService {
    Page<Order> findAll(Pageable pageable);
}
