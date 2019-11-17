package ru.ryazan.senatorshop.core.service;

import ru.ryazan.senatorshop.core.domain.admin.SalesEvents;

import java.util.List;
import java.util.Optional;

public interface SaleEventsService {
    List<SalesEvents> findAll();
    Optional<SalesEvents> findById(Long id);
    void deleteById(Long id);
}
