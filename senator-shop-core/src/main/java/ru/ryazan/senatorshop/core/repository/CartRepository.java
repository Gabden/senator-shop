package ru.ryazan.senatorshop.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ryazan.senatorshop.core.domain.cart.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
