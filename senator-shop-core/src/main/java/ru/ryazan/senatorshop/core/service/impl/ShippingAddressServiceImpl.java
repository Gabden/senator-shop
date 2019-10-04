package ru.ryazan.senatorshop.core.service.impl;

import org.springframework.stereotype.Service;
import ru.ryazan.senatorshop.core.domain.address.ShippingAddress;
import ru.ryazan.senatorshop.core.repository.ShippingAddressRepository;
import ru.ryazan.senatorshop.core.service.ShippingAddressService;
@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {
    private ShippingAddressRepository shippingAddressRepository;

    public ShippingAddressServiceImpl(ShippingAddressRepository shippingAddressRepository) {
        this.shippingAddressRepository = shippingAddressRepository;
    }

    @Override
    public void addShippingAddressBilling(ShippingAddress s) {
        shippingAddressRepository.save(s);
    }
}
