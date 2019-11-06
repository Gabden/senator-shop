package ru.ryazan.senatorshop.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.ryazan.senatorshop.core.domain.Customer;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    Customer findCustomerByCustomerName(String name);
    Page<Customer> findCustomersByCustomerPhone(String phone, Pageable pageable);

}
