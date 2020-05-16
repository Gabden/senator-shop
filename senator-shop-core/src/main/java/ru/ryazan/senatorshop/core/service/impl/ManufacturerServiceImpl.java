package ru.ryazan.senatorshop.core.service.impl;

import org.springframework.stereotype.Service;
import ru.ryazan.senatorshop.core.domain.details.Manufacturer;
import ru.ryazan.senatorshop.core.repository.ManufacturerRepository;
import ru.ryazan.senatorshop.core.service.ManufacturerService;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    private ManufacturerRepository repository;

    public ManufacturerServiceImpl(ManufacturerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Manufacturer> findManufacturersByManufacturerEquals(String manufacturer) {
        return repository.findManufacturersByManufacturerEquals(manufacturer);
    }

    @Override
    public void create(Manufacturer manufacturer) {
        repository.save(manufacturer);
    }
}
