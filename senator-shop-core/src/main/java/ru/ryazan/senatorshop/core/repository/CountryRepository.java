package ru.ryazan.senatorshop.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ryazan.senatorshop.core.domain.details.Country;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {
    List<Country> findCountriesByCountryEquals(String country);
}
