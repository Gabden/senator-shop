package ru.ryazan.senatorshop.core.service.impl;

import org.springframework.stereotype.Service;
import ru.ryazan.senatorshop.core.domain.address.BillingAddress;
import ru.ryazan.senatorshop.core.repository.BillingAddressRepository;
import ru.ryazan.senatorshop.core.service.BillingAddressService;

@Service
public class BillingAddressServiceImpl implements BillingAddressService {
    private BillingAddressRepository billingAddressRepository;

    public BillingAddressServiceImpl(BillingAddressRepository billingAddressRepository) {
        this.billingAddressRepository = billingAddressRepository;
    }

    @Override
    public void addBillingAddress(BillingAddress b) {
        billingAddressRepository.save(b);
    }
}
