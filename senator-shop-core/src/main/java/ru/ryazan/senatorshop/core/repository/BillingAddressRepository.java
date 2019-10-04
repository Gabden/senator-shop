package ru.ryazan.senatorshop.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ryazan.senatorshop.core.domain.address.BillingAddress;

public interface BillingAddressRepository extends JpaRepository<BillingAddress, Long> {
}
