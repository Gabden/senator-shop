package ru.ryazan.senatorshop.core.service;

import ru.ryazan.senatorshop.core.domain.admin.PhotoOfEvent;

import java.util.List;

public interface PhotoOfEventService {
    List<PhotoOfEvent> findAll();
    void save(PhotoOfEvent event);
    void deleteById(Long id);
    List<PhotoOfEvent> findByLifeEvents_Id(Long id);
    List<PhotoOfEvent> findAllByLifeEvents_Id(Long id);
}
