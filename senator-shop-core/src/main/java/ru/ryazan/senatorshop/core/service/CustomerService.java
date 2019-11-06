package ru.ryazan.senatorshop.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.ryazan.senatorshop.core.domain.Customer;

import java.util.Optional;

public interface CustomerService {
    void addCustomer(Customer customer);
    Optional<Customer> getCustomerById(Long id);
    Page<Customer> getAllCustomers(Pageable pageable);
    Customer findCustomerByCustomerName(String name);

    Page<Customer> findCustomersByCustomerPhone(String phone, Pageable pageable);
}

