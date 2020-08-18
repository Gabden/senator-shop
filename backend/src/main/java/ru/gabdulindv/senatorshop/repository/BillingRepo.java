package ru.gabdulindv.senatorshop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.gabdulindv.senatorshop.model.address.BillingAddress;

public interface BillingRepo extends JpaRepository<BillingAddress, Long> {
}
