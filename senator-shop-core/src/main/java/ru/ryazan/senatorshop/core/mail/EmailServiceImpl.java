package ru.ryazan.senatorshop.core.mail;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import ru.ryazan.senatorshop.core.domain.cart.Cart;
import ru.ryazan.senatorshop.core.domain.cart.CartItem;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;


@Component
public class EmailServiceImpl implements EmailService{
    private JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String[] sendTo, Cart cart) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(sendTo);
        String message = "Оформлен новый заказ, id =  " + cart.getCartId() + "\n";
        for (CartItem item : cart.getCartItems()){
            message +="Продукт : \n" + "id : " + item.getProduct().getId() + "  название : " + item.getProduct().getProductName()
                    + " цена : " + item.getProduct().getProductPrice() + "  количество : " + item.getQuantity() + "\n";
        }
        message += "Итого: " + cart.getGrandTotal();
        msg.setSubject("Заказ на сайте senator-wine.ru");
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
