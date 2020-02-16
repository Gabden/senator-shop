package ru.ryazan.senatorshop.core.mail;

import ru.ryazan.senatorshop.core.domain.cart.Cart;

public interface EmailService {
    void sendEmail(Cart cart, Long orderId);

    void sendRestoreMail(String mail, String password);
}
