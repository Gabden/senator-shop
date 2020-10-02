package ru.gabdulindv.senatorshop.service.mail;

import ru.gabdulindv.senatorshop.model.order.Order;

public interface EmailService {
    void sendRegistrationEmail(String mailTo);

    void sendOrderEmail(Order order);

    void sendRestoreMail(String mail, String password);
}
