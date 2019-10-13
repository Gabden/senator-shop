package ru.ryazan.senatorshop.web;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.ryazan.senatorshop.core.domain.User;
import ru.ryazan.senatorshop.core.service.UserService;

@SpringBootApplication(scanBasePackages = {"ru.ryazan.senatorshop.web"
        ,"ru.ryazan.senatorshop.core"})
public class SenatorShopApplication implements ApplicationRunner {
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public SenatorShopApplication(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(SenatorShopApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = userService.findUserByUsername("admin");
        if (user == null){
            User newAdmin = new User();
            newAdmin.setActive(1);
            newAdmin.setEmail("gabden5545@gmail.com");
            newAdmin.setRoles("ADMIN");
            newAdmin.setUsername("admin");
            newAdmin.setPassword(passwordEncoder.encode("123"));
            userService.save(newAdmin);
        }

    }
}
