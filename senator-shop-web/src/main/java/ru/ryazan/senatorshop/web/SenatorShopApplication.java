package ru.ryazan.senatorshop.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ru.ryazan.senatorshop.web"
        ,"ru.ryazan.senatorshop.core"})
public class SenatorShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SenatorShopApplication.class, args);
    }

}
