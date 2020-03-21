package ru.ryazan.senatorshop.core.service.impl;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.ryazan.senatorshop.core.domain.admin.SalesEvents;
import ru.ryazan.senatorshop.core.repository.SaleEventsRepository;
import ru.ryazan.senatorshop.core.service.SaleEventsService;

import java.util.List;
import java.util.Optional;
@Service
public class SaleEventsServiceImpl implements SaleEventsService {
    private SaleEventsRepository repository;

    public SaleEventsServiceImpl(SaleEventsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SalesEvents> findAll() {
        return repository.findAll(Sort.by("id").descending());
    }

    @Override
    public Optional<SalesEvents> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void save(SalesEvents events) {
        repository.save(events);
    }
}
