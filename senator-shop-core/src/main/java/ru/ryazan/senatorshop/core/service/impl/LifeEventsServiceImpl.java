package ru.ryazan.senatorshop.core.service.impl;

import org.springframework.stereotype.Service;
import ru.ryazan.senatorshop.core.domain.admin.LifeEvents;
import ru.ryazan.senatorshop.core.repository.LifeEventsRepository;
import ru.ryazan.senatorshop.core.service.LifeEventsService;

import java.util.List;
@Service
public class LifeEventsServiceImpl implements LifeEventsService {
    private LifeEventsRepository repository;

    public LifeEventsServiceImpl(LifeEventsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(LifeEvents lifeEvents) {
        repository.save(lifeEvents);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<LifeEvents> findAll() {
        return repository.findAll();
    }

    @Override
    public void update(LifeEvents lifeEvents) {
        repository.save(lifeEvents);
    }

    @Override
    public LifeEvents findByNameOfEvent(String nameOfEvent) {
        return repository.findByNameOfEvent(nameOfEvent);
    }
}
