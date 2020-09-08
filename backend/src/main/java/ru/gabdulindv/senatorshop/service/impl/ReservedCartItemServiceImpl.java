package ru.gabdulindv.senatorshop.service.impl;

import org.springframework.stereotype.Service;
import ru.gabdulindv.senatorshop.model.order.ReservedCartItem;
import ru.gabdulindv.senatorshop.repository.order.ReservedCartItemRepository;
import ru.gabdulindv.senatorshop.service.ReservedCartItemService;

import java.util.Optional;

@Service
public class ReservedCartItemServiceImpl implements ReservedCartItemService {
    private ReservedCartItemRepository reservedCartItemRepository;

    public ReservedCartItemServiceImpl(ReservedCartItemRepository reservedCartItemRepository) {
        this.reservedCartItemRepository = reservedCartItemRepository;
    }

    @Override
    public Optional<ReservedCartItem> findById(Long id) {
        return reservedCartItemRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        reservedCartItemRepository.deleteById(id);
    }
}
