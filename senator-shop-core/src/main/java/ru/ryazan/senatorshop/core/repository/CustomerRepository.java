package ru.ryazan.senatorshop.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ryazan.senatorshop.core.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByCustomerNameAndCustomerEmail(String name, String email);
    Customer findCustomerByCustomerName(String name);
}
