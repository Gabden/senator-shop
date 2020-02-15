package ru.ryazan.senatorshop.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.ryazan.senatorshop.core.domain.Customer;
import ru.ryazan.senatorshop.core.domain.CustomerOrder;
import ru.ryazan.senatorshop.core.domain.cart.Cart;

import java.util.List;

public interface CustomerOrderRepository extends PagingAndSortingRepository<CustomerOrder, Long> {
    Page<CustomerOrder> findAllByCustomer(Customer customer, Pageable pageable);
    List<CustomerOrder> findCustomerOrderByCart(Cart cart);

    List<CustomerOrder> findCustomerOrdersByStatus(String status);
}
