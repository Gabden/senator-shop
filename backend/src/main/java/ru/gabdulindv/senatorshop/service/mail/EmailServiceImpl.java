package ru.gabdulindv.senatorshop.service.mail;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.gabdulindv.senatorshop.model.order.Order;
import ru.gabdulindv.senatorshop.model.order.OrderItem;
import ru.gabdulindv.senatorshop.model.order.ReservedCart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {
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
        sendMail(mailRegistrationTemplateId, mailTo, "", null);
    }

    @Override
    public void sendOrderEmail(Order order) {
        sendMail(mailOrderTemplateId, order.getUser().getUsername(), "", order.getReservedCart());
        sendMail(mailOrderTemplateId, adminMail, "", order.getReservedCart());
    }

    @Override
    public void sendRestoreMail(String mail, String password) {
        sendMail(mailChangePasswordTemplateId, mail, password, null);
    }


    private void sendMail(String templateId, String mailTo, String newPassword, ReservedCart cart) {
        SendGrid sendGrid = new SendGrid(apiKey);
        Mail mail = prepareMail(templateId, mailTo, newPassword, cart);
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

    private Mail prepareMail(String templateId, String mailTo, String newPassword, ReservedCart cart) {
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
            cart.getReservedCartItems().forEach(cartItem -> {
                OrderItem orderItem = new OrderItem(cartItem.getProduct().getProductId(), cartItem.getCartItemFinalPrice(), cartItem.getQuantity(), cartItem.getProduct().getProductName());
                orderItems.add(orderItem);
            });
            personalization.addDynamicTemplateData("orderItems", orderItems);
            personalization.addDynamicTemplateData("grandTotal", cart.getGrandTotal());
            personalization.addDynamicTemplateData("orderId", cart.getOrder().getOrderId());
            personalization.addDynamicTemplateData("phone", cart.getOrder().getUser().getUserDetailsDescription().getPhone());
        }
        personalization.addTo(to);
        mail.addPersonalization(personalization);

        mail.setTemplateId(templateId);
        return mail;
    }
}
