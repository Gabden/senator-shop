package ru.ryazan.senatorshop.core.service.impl;

import org.springframework.stereotype.Service;
import ru.ryazan.senatorshop.core.domain.Favorites;
import ru.ryazan.senatorshop.core.repository.FavoritesRepository;
import ru.ryazan.senatorshop.core.service.FavoritesService;
@Service
public class FavoritesServiceImpl implements FavoritesService {
    private FavoritesRepository favoritesRepository;

    public FavoritesServiceImpl(FavoritesRepository favoritesRepository) {
        this.favoritesRepository = favoritesRepository;
    }

    @Override
    public void addToFavorite(Favorites favorites) {
        favoritesRepository.save(favorites);
    }

    @Override
    public void deleteFromFavourite(Long id) {
        favoritesRepository.deleteById(id);
    }

    @Override
    public void deleteFromFavourite(Favorites favorites) {
        favoritesRepository.delete(favorites);
    }
}
