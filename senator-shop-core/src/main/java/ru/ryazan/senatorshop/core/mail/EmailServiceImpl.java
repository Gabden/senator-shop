package ru.ryazan.senatorshop.core.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import ru.ryazan.senatorshop.core.domain.ProductImage;
import ru.ryazan.senatorshop.core.domain.cart.Cart;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
public class EmailServiceImpl implements EmailService {
    private JavaMailSender javaMailSender;
    private MailContentBuilder mailContentBuilder;

    @Value("${admin.mail}")
    private String adminsMail;

    @Value("${SENATOR_TECH_MAIL}")
    private String adminsMailTech;


    public EmailServiceImpl(JavaMailSender javaMailSender, MailContentBuilder mailContentBuilder) {
        this.javaMailSender = javaMailSender;
        this.mailContentBuilder = mailContentBuilder;
    }

    @Override
    public void sendEmail(Cart cart, Long orderId) {
        sendEmailToCustomerAndAdmin(cart, true, orderId);
        sendEmailToCustomerAndAdmin(cart, false, orderId);

    }

    private void sendEmailToCustomerAndAdmin(Cart cart, boolean isCustomerOrAdmin, Long orderId) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            String content = mailContentBuilder.build(cart, orderId);
            String[] mails = adminsMail.split(";");
            messageHelper.setText(content, true);

            cart.getCartItems().forEach(cartItem -> {
                List<ProductImage> images = new ArrayList<>(cartItem.getProduct().getProductImageSet());
                try {
                    messageHelper.addInline("img-" + cartItem.getCartItemId(), new ByteArrayResource(images.get(0).getFileData()), images.get(0).getFileType());
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });

            messageHelper.setFrom(adminsMailTech);
            if (isCustomerOrAdmin) {
                messageHelper.setTo(cart.getCustomer().getCustomerName());
            } else {
                messageHelper.setTo(mails[0]);
            }

            messageHelper.setSubject("Резерв товара на сайте senator-wine.ru");
        };

            javaMailSender.send(messagePreparator);

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
