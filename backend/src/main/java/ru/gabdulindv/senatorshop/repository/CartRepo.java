package ru.gabdulindv.senatorshop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.gabdulindv.senatorshop.model.cart.Cart;

public interface CartRepo extends JpaRepository<Cart, Long> {
}
