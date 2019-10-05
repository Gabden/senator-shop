package ru.ryazan.senatorshop.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ryazan.senatorshop.core.domain.CustomerOrder;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
}
