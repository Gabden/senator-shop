package ru.ryazan.senatorshop.core.mail;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import ru.ryazan.senatorshop.core.domain.cart.Cart;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;


@Component
public class EmailServiceImpl implements EmailService{
    private JavaMailSender javaMailSender;
    private MailContentBuilder mailContentBuilder;

    public EmailServiceImpl(JavaMailSender javaMailSender, MailContentBuilder mailContentBuilder) {
        this.javaMailSender = javaMailSender;
        this.mailContentBuilder = mailContentBuilder;
    }

    @Override
    public void sendEmail(String[] sendTo, Cart cart) {
        sendEmailToCustomerAndAdmin(cart, true);
        sendEmailToCustomerAndAdmin(cart, false);

    }

    private void sendEmailToCustomerAndAdmin(Cart cart, boolean isCustomerOrAdmin) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            String content = mailContentBuilder.build(cart);
            messageHelper.setText(content, true);
            messageHelper.setFrom("aisukhov@mail.ru");
            if (isCustomerOrAdmin) {
                messageHelper.setTo(cart.getCustomer().getCustomerName());
            } else {
                messageHelper.setTo("gabden5545@gmail.com");
            }

            messageHelper.setSubject("Резерв товара на сайте senator-wine.ru");
        };
        try {
            javaMailSender.send(messagePreparator);
        } catch (MailException e) {
            // runtime exception; compiler will not force you to handle it
        }
    }

    @Override
    public void sendRestoreMail(String mail, String password) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mail);
        msg.setSubject("Восстановление пароля на сайте senator-wine.ru");
        String message = "Для вас создан новый пароль : " + password + "\n Вы его можете сменить в личном кабинете на сайте";
        msg.setText(message);

        javaMailSender.send(msg);
    }

    public void sendEmailWithAttachment() throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo("1@gmail.com");

        helper.setSubject("Testing from Spring Boot");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1>Check attachment for image!</h1>", true);

        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

        javaMailSender.send(msg);

    }
}
