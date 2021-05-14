package ru.gabdulindv.senatorshop.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gabdulindv.senatorshop.model.order.ReservedCart;

public interface ReservedCartRepository extends JpaRepository<ReservedCart, Long> {
}
