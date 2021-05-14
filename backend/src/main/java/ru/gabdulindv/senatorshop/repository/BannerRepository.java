package ru.gabdulindv.senatorshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gabdulindv.senatorshop.model.sales.Banner;

public interface BannerRepository extends JpaRepository<Banner, Long> {
}
