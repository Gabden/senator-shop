package ru.ryazan.gabden.senatorshop.service;

import ru.ryazan.gabden.senatorshop.domain.User;

import java.util.List;

public interface UserService {
    void save(User user);
    List<User> findAll();
}
