package ru.ryazan.senatorshop.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ryazan.senatorshop.core.domain.Favorites;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
}
