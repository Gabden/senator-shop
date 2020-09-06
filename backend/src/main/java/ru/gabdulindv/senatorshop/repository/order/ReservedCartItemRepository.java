package ru.gabdulindv.senatorshop.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gabdulindv.senatorshop.model.order.ReservedCartItem;

public interface ReservedCartItemRepository extends JpaRepository<ReservedCartItem, Long> {
}
