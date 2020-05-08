package ru.gabdulindv.senatorshop.repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gabdulindv.senatorshop.model.User;

import java.util.Arrays;
import java.util.List;

@Service
public class DBInit implements CommandLineRunner {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public DBInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Delete all
        this.userRepository.deleteAll();

        // Crete users
        User dan = new User("dan", passwordEncoder.encode("dan123"), "USER", "");
        User admin = new User("admin", passwordEncoder.encode("admin123"), "ADMIN", "ACCESS_TEST1,ACCESS_TEST2");
        User manager = new User("manager", passwordEncoder.encode("manager123"), "MANAGER", "ACCESS_TEST1");

        List<User> users = Arrays.asList(dan, admin, manager);

        // Save to db
        this.userRepository.saveAll(users);
    }
}