package ru.gabdulindv.senatorshop.service;

import ru.gabdulindv.senatorshop.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    User findByUsername(String username);

    void deleteById(Long id);
}
