package ru.ryazan.senatorshop.core.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.ryazan.senatorshop.core.domain.admin.SalesEvents;

public interface SaleEventsRepository extends JpaRepository<SalesEvents, Long> {
}
