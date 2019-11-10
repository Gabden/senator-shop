package ru.ryazan.senatorshop.core.mail;

import ru.ryazan.senatorshop.core.domain.cart.Cart;

public interface EmailService {
    void sendEmail(String[] sendTo, Cart cart);
    void sendRestoreMail(String mail, String password);
}
