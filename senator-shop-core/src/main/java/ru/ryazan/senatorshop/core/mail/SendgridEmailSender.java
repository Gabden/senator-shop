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
        sendMail(mailRegistrationTemplateId, mailTo);
    }

    @Override
    public void sendOrderEmail(Cart cart, Long orderId) {

    }

    @Override
    public void sendRestoreMail(String mail, String password) {

    }


    public void sendMail(String templateId, String mailTo) {
        SendGrid sendGrid = new SendGrid(apiKey);
        Mail mail = prepareMail(templateId, mailTo);
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

    private Mail prepareMail(String templateId, String mailTo) {
        Mail mail = new Mail();
        Email from = new Email("senator24a@gmail.com");
        Email to = new Email(mailTo);
        mail.setFrom(from);
        Personalization personalization = new Personalization();
        personalization.addDynamicTemplateData("orderId", "4125");
        personalization.addTo(to);
        mail.addPersonalization(personalization);

        mail.setTemplateId(templateId);
        return mail;
    }
}
