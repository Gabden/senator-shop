package ru.gabdulindv.senatorshop.service;

import java.util.Set;

public interface ProductDetailsService {
    Set<String> findAllTypes();

    Set<String> findAllManufacturers();

    Set<String> findAllCountries();
}
