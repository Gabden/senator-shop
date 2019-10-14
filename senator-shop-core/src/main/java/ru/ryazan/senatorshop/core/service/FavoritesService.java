package ru.ryazan.senatorshop.core.service;

import ru.ryazan.senatorshop.core.domain.Favorites;

public interface FavoritesService {
    void addToFavorite(Favorites favorites);
    void deleteFromFavourite(Long id);
    void deleteFromFavourite(Favorites favorites);
}
