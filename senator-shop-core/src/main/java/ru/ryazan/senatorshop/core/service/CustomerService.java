package ru.ryazan.senatorshop.core.service;

import ru.ryazan.senatorshop.core.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    void addCustomer(Customer customer);
    Optional<Customer> getCustomerById(Long id);
    List<Customer> getAllCustomers();
    Customer findCustomerByCustomerNameAndCustomerEmail(String name, String mail);
    Customer findCustomerByCustomerName(String name);
}
