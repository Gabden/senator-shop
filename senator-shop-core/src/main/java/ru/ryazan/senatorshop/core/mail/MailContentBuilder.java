package ru.ryazan.senatorshop.core.mail;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import ru.ryazan.senatorshop.core.domain.cart.Cart;

@Service
public class MailContentBuilder {

    private TemplateEngine templateEngine;

    public MailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String build(Cart cart) {
        Context context = new Context();
        context.setVariable("cart", cart);
        return templateEngine.process("mailTemplate", context);
    }

}