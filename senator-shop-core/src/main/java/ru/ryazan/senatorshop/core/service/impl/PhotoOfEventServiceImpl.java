package ru.ryazan.senatorshop.core.service.impl;

import org.springframework.stereotype.Service;
import ru.ryazan.senatorshop.core.domain.admin.PhotoOfEvent;
import ru.ryazan.senatorshop.core.repository.PhotoOfEventRepository;
import ru.ryazan.senatorshop.core.service.PhotoOfEventService;

import java.util.List;

@Service
public class PhotoOfEventServiceImpl implements PhotoOfEventService {
    private PhotoOfEventRepository repository;

    public PhotoOfEventServiceImpl(PhotoOfEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PhotoOfEvent> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(PhotoOfEvent event) {
        repository.save(event);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<PhotoOfEvent> findByLifeEvents_Id(Long id) {
        return repository.findByLifeEvents_Id(id);
    }

    @Override
    public List<PhotoOfEvent> findAllByLifeEvents_Id(Long id) {
        return repository.findAllByLifeEvents_Id(id);
    }
}
