package ru.gabdulindv.senatorshop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.gabdulindv.senatorshop.model.address.ShippingAddress;

public interface ShippingRepo extends JpaRepository<ShippingAddress, Long> {
}
