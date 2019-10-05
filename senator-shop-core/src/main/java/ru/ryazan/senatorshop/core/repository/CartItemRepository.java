package ru.ryazan.senatorshop.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ryazan.senatorshop.core.domain.Product;
import ru.ryazan.senatorshop.core.domain.cart.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    void deleteCartItemByProductId(Long id);
}
