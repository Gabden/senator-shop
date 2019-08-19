package ru.ryazan.senatorshop.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.ryazan.senatorshop.core.storage.StorageProperties;

@SpringBootApplication(scanBasePackages = {"ru.ryazan.senatorshop.web"
        ,"ru.ryazan.senatorshop.core"})
@EnableConfigurationProperties(StorageProperties.class)
public class SenatorShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SenatorShopApplication.class, args);
    }

}
