package ru.gabdulindv.senatorshop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.gabdulindv.senatorshop.model.User;

import java.util.Optional;

public interface UserService {
    void save(User user);

    Page<User> findAll(Pageable pageable);

    Optional<User> findById(Long id);

    User findByUsername(String username);

    void deleteById(Long id);

    Optional<User> findUserByUserDetailsDescription_PhoneContains(String phone);

    Optional<User> findUserByUsernameContains(String username);
}
