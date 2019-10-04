package ru.ryazan.senatorshop.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ryazan.senatorshop.core.domain.address.ShippingAddress;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {
}

