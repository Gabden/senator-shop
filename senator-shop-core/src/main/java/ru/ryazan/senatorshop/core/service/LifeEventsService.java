package ru.ryazan.senatorshop.core.service;

import ru.ryazan.senatorshop.core.domain.admin.LifeEvents;

import java.util.List;

public interface LifeEventsService {
    void save(LifeEvents lifeEvents);
    void deleteById(Long id);
    List<LifeEvents> findAll();
    void update(LifeEvents lifeEvents);
    LifeEvents findByNameOfEvent(String nameOfEvent);
}
