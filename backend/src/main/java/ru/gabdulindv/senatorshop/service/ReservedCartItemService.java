package ru.gabdulindv.senatorshop.service;

import ru.gabdulindv.senatorshop.model.order.ReservedCartItem;

import java.util.Optional;

public interface ReservedCartItemService {
    Optional<ReservedCartItem> findById(Long id);

    void deleteById(Long id);
}
