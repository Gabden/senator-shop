package ru.gabdulindv.senatorshop.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gabdulindv.senatorshop.model.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
