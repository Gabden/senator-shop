package ru.gabdulindv.senatorshop.repository.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.gabdulindv.senatorshop.model.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findOrdersByUser_UserDetailsDescription_PhoneContains(String phone, Pageable pageable);

    Page<Order> findOrdersByUser_UsernameContains(String email, Pageable pageable);
}
