package ru.gabdulindv.senatorshop.service.impl;

import org.springframework.stereotype.Service;
import ru.gabdulindv.senatorshop.model.sales.Banner;
import ru.gabdulindv.senatorshop.repository.BannerRepository;
import ru.gabdulindv.senatorshop.service.BannerService;

import java.util.List;
import java.util.Optional;

@Service
public class BannerServiceImpl implements BannerService {
    private BannerRepository bannerRepository;

    public BannerServiceImpl(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }

    @Override
    public List<Banner> findAll() {
        return bannerRepository.findAll();
    }

    @Override
    public void save(Banner banner) {
        bannerRepository.save(banner);
    }

    @Override
    public void deleteById(Long id) {
        bannerRepository.deleteById(id);
    }

    @Override
    public Optional<Banner> findById(Long id) {
        return bannerRepository.findById(id);
    }
}
