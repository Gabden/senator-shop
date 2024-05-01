package ru.gabdulindv.senatorshop.service.impl;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.gabdulindv.senatorshop.model.order.Order;
import ru.gabdulindv.senatorshop.service.WebclientService;

import java.net.URI;
import java.util.HashMap;

@Service
public class WebclientServiceImpl implements WebclientService {
    WebClient webClient;

    public WebclientServiceImpl() {
        this.webClient = WebClient.create();
    }

    @Override
    public void resetPasswordRequest(String email, String name, String newPassword) {
        HashMap<String, String> bodyValues = new HashMap<>();

        bodyValues.put("email", email);
        bodyValues.put("name", name);
        bodyValues.put("new_password", newPassword);

        try {
            webClient.post()
                    .uri(new URI("http://localhost:8099/api/v1/mail/wine/reset-password"))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .bodyValue(bodyValues)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createOrderRequest(String email, String name, Order order) {
        HashMap bodyValues = new HashMap<>();

        bodyValues.put("email", email);
        bodyValues.put("name", name);
        bodyValues.put("order", order);

        try {
            webClient.post()
                    .uri(new URI("http://localhost:8099/api/v1/mail/wine/order"))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .bodyValue(bodyValues)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
