package ru.ryazan.senatorshop.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ryazan.senatorshop.core.domain.details.Manufacturer;

import java.util.List;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    List<Manufacturer> findManufacturersByManufacturerEquals(String manufacturer);
}
