package ru.ryazan.senatorshop.core.service;

import ru.ryazan.senatorshop.core.domain.User;

import java.util.List;

public interface UserService {
    void save(User user);
    List<User> findAll();
}
