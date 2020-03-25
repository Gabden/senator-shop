package ru.ryazan.senatorshop.core.mail;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.ryazan.senatorshop.core.domain.cart.Cart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SendgridEmailSender implements EmailService {

    @Value("${admin.mail}")
    private String adminMail;

    @Value("${sendgrid.api.key}")
    private String apiKey;

    @Value("${sendgrid.template.registration.id}")
    private String mailRegistrationTemplateId;

    @Value("${sendgrid.template.changepwd.id}")
    private String mailChangePasswordTemplateId;

    @Value("${sendgrid.template.order.id}")
    private String mailOrderTemplateId;


    @Override
    public void sendRegistrationEmail(String mailTo) {
        sendMail(mailRegistrationTemplateId, mailTo, "", null, 0L);
    }

    @Override
    public void sendOrderEmail(Cart cart, Long orderId) {
        sendMail(mailOrderTemplateId, cart.getCustomer().getCustomerName(), "", cart, orderId);
        sendMail(mailOrderTemplateId, adminMail, "", cart, orderId);
    }

    @Override
    public void sendRestoreMail(String mail, String password) {
        sendMail(mailChangePasswordTemplateId, mail, password, null, 0L);
    }


    public void sendMail(String templateId, String mailTo, String newPassword, Cart cart, Long orderId) {
        SendGrid sendGrid = new SendGrid(apiKey);
        Mail mail = prepareMail(templateId, mailTo, newPassword, cart, orderId);
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        try {
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            System.out.println("---" + response.getHeaders());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private Mail prepareMail(String templateId, String mailTo, String newPassword, Cart cart, Long orderId) {
        Mail mail = new Mail();
        if (mailTo.equals(adminMail)) {
            Email from = new Email("service@senator-wine.ru");
            mail.setFrom(from);
        } else {
            Email from = new Email(adminMail);
            mail.setFrom(from);
        }

        Email to = new Email(mailTo);

        Personalization personalization = new Personalization();
        personalization.addDynamicTemplateData("password", newPassword);
        if (cart != null) {
            List<OrderItem> orderItems = new ArrayList<>();
            cart.getCartItems().forEach(cartItem -> {
                OrderItem orderItem = new OrderItem(cartItem.getProduct().getId(), cartItem.getCartItemFinalPrice(), cartItem.getQuantity(), cartItem.getProduct().getProductName());
                orderItems.add(orderItem);
            });
            personalization.addDynamicTemplateData("orderItems", orderItems);
            personalization.addDynamicTemplateData("grandTotal", cart.getGrandTotal());
            personalization.addDynamicTemplateData("orderId", orderId);
            personalization.addDynamicTemplateData("phone", cart.getCustomer().getCustomerPhone());
        }
        personalization.addTo(to);
        mail.addPersonalization(personalization);

        mail.setTemplateId(templateId);
        return mail;
    }
}
