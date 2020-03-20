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

import java.io.IOException;

@Component
public class SendgridEmailSender {

    @Value("${admin.mail}")
    private String adminMail;

    @Value("${sendgrid.api.key}")
    private String apiKey;

    @Value("${sendgrid.template.id}")
    private String mailTemplateId;

    public void sendMail() {
        SendGrid sendGrid = new SendGrid(apiKey);
        Mail mail = prepareMail();
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

    private Mail prepareMail() {
        Mail mail = new Mail();
        Email from = new Email("senator24a@gmail.com");
        Email to = new Email("gabden5545@gmail.com");
        mail.setFrom(from);
        Personalization personalization = new Personalization();
        personalization.addDynamicTemplateData("orderId", "4125");
        personalization.addTo(to);
        mail.addPersonalization(personalization);

        mail.setTemplateId(mailTemplateId);
        return mail;
    }
}
