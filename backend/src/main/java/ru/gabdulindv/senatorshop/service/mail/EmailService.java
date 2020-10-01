package ru.gabdulindv.senatorshop.service.mail;

import ru.gabdulindv.senatorshop.model.cart.Cart;

public interface EmailService {
    void sendRegistrationEmail(String mailTo);

    void sendOrderEmail(Cart cart, Long orderId);

    void sendRestoreMail(String mail, String password);
}
