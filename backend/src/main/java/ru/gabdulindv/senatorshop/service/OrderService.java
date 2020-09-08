package ru.gabdulindv.senatorshop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.gabdulindv.senatorshop.model.order.Order;

import java.util.Optional;

public interface OrderService {
    Page<Order> findAll(Pageable pageable);

    Optional<Order> findById(Long id);

    Page<Order> findOrdersByUser_UserDetailsDescription_Phone(String phone, Pageable pageable);

    Page<Order> findOrdersByUser_UsernameContains(String email, Pageable pageable);

    void deleteById(Long id);

    void saveOrUpdate(Order order);
}
