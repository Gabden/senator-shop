package ru.ryazan.senatorshop.core.service.impl;

import org.springframework.stereotype.Service;
import ru.ryazan.senatorshop.core.domain.details.Country;
import ru.ryazan.senatorshop.core.repository.CountryRepository;
import ru.ryazan.senatorshop.core.service.CountryService;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private CountryRepository repository;

    public CountryServiceImpl(CountryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Country> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Country> findCountriesByCountryEquals(String country) {
        return repository.findCountriesByCountryEquals(country);
    }

    @Override
    public void create(Country country) {
        repository.save(country);
    }
}
