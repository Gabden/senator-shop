package ru.ryazan.senatorshop.core.config;

import org.springframework.stereotype.Component;

@Component
public class PriceFormatter {
    public String format(String numberString) {
        try {
            int number = Integer.parseInt(numberString);
            number *= 0.9;
            return String.valueOf(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return numberString;
    }
}
