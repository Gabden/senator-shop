package ru.ryazan.senatorshop.core.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.ryazan.senatorshop.core.domain.Customer;
import ru.ryazan.senatorshop.core.repository.CustomerRepository;
import ru.ryazan.senatorshop.core.service.CustomerService;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Page<Customer> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }


    @Override
    public Customer findCustomerByCustomerName(String name) {
        return customerRepository.findCustomerByCustomerName(name);
    }

    @Override
    public Page<Customer> findCustomersByCustomerPhone(String phone, Pageable pageable) {
        return customerRepository.findCustomersByCustomerPhone(phone, pageable);
    }


}
