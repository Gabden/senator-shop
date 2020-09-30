package ru.gabdulindv.senatorshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gabdulindv.senatorshop.model.cart.CartItem;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {
}
