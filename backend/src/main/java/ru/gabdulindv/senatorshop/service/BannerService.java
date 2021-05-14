package ru.gabdulindv.senatorshop.service;

import ru.gabdulindv.senatorshop.model.sales.Banner;

import java.util.List;
import java.util.Optional;

public interface BannerService {
    List<Banner> findAll();

    void save(Banner banner);

    void deleteById(Long id);

    Optional<Banner> findById(Long id);
}
