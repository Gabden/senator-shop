package ru.gabdulindv.senatorshop.service;

import ru.gabdulindv.senatorshop.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    List<User> findAll();

    User findByUsername(String username);

    void deleteById(Long id);
}
