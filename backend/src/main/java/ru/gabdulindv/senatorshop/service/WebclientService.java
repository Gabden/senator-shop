package ru.gabdulindv.senatorshop.service;

import ru.gabdulindv.senatorshop.model.order.Order;

public interface WebclientService {
    void resetPasswordRequest(String email, String name, String newPassword);
    void createOrderRequest(String email, String name, Order order);
}
