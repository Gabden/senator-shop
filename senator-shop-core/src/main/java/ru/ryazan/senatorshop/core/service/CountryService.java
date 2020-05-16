package ru.ryazan.senatorshop.core.service;

import ru.ryazan.senatorshop.core.domain.details.Country;

import java.util.List;

public interface CountryService {
    List<Country> findAll();

    List<Country> findCountriesByCountryEquals(String country);

    void create(Country country);
}
