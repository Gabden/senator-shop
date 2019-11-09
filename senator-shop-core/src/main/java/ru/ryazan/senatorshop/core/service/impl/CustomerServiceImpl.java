package ru.ryazan.senatorshop.core.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.ryazan.senatorshop.core.domain.Customer;
import ru.ryazan.senatorshop.core.domain.User;
import ru.ryazan.senatorshop.core.repository.CustomerRepository;
import ru.ryazan.senatorshop.core.service.CustomerService;
import ru.ryazan.senatorshop.core.service.UserService;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private UserService userService;

    public CustomerServiceImpl(CustomerRepository customerRepository, UserService userService) {
        this.customerRepository = customerRepository;
        this.userService = userService;
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

    public void validate(Customer customer){
        Optional<Customer> oldCustomer = customerRepository.findById(customer.getCustomerId());
        if (oldCustomer.isPresent()){

            if (!customer.getCustomerName().equals(oldCustomer.get().getCustomerName())){
                oldCustomer.get().setCustomerName(customer.getCustomerName());
                User user = userService.findUserByUsername(oldCustomer.get().getCustomerName());
                user.setUsername(customer.getCustomerName());
                userService.save(user);
                customerRepository.save(oldCustomer.get());
            }

            if (!customer.getCustomerPhone().equals(oldCustomer.get().getCustomerPhone())){
                oldCustomer.get().setCustomerPhone(customer.getCustomerPhone());
                customerRepository.save(oldCustomer.get());
            }
        }
    }


}
