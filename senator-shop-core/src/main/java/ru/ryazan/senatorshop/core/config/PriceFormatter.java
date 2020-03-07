package ru.ryazan.senatorshop.core.config;

import org.springframework.stereotype.Component;
import ru.ryazan.senatorshop.core.domain.Product;

@Component
public class PriceFormatter {
    public String format(Product product) {
        try {
            int discount = product.getDiscount();
            String price = product.getProductPrice();
            int priceNumber = Integer.parseInt(price);
            priceNumber = priceNumber - (priceNumber * discount) / 100;

            return String.valueOf(priceNumber);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return product.getProductPrice();
    }
}
