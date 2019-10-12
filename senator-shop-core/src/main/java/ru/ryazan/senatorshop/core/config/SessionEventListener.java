package ru.ryazan.senatorshop.core.config;

import org.springframework.stereotype.Component;
import ru.ryazan.senatorshop.core.domain.cart.Cart;
import ru.ryazan.senatorshop.core.service.CartService;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Optional;
import java.util.UUID;

@Component
public class SessionEventListener implements HttpSessionListener {
    private CartService cartService;

    public SessionEventListener(CartService cartService) {
        this.cartService = cartService;
    }

    //Notification that a session was created.
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionCreatedEvent) {
        UUID uuid = UUID.randomUUID();
        httpSessionCreatedEvent.getSession().setAttribute("USERSESSION", uuid);
        System.out.println("----------------------- session created w uuid" + uuid.toString());
    }

    //Notification that a session is about to be invalidated.
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionDestroyedEvent) {
        System.out.println("----------------------- session destroyed");
        Optional<Cart> cart = cartService.readBySessionId(String.valueOf(httpSessionDestroyedEvent.getSession().getAttribute("USERSESSION")));
        cart.ifPresent(value -> cartService.delete(value.getCartId()));
        System.out.println("----------------------- cart session destroyed");
    }

}