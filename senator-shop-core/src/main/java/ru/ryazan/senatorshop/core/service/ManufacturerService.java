package ru.ryazan.senatorshop.core.service;

import ru.ryazan.senatorshop.core.domain.details.Manufacturer;

import java.util.List;

public interface ManufacturerService {
    List<Manufacturer> findAll();

    List<Manufacturer> findManufacturersByManufacturerEquals(String manufacturer);

    void create(Manufacturer manufacturer);
}
