package ru.gabdulindv.senatorshop.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.gabdulindv.senatorshop.model.order.Order;
import ru.gabdulindv.senatorshop.repository.order.OrderRepository;
import ru.gabdulindv.senatorshop.service.OrderService;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Page<Order> findOrdersByUser_UserDetailsDescription_Phone(String phone, Pageable pageable) {
        return orderRepository.findOrdersByUser_UserDetailsDescription_PhoneContains(phone, pageable);
    }

    @Override
    public Page<Order> findOrdersByUser_UsernameContains(String email, Pageable pageable) {
        return orderRepository.findOrdersByUser_UsernameContains(email, pageable);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void saveOrUpdate(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Long countOrdersByStatusContains(String status) {
        return orderRepository.countOrdersByStatusContains(status);
    }
}
