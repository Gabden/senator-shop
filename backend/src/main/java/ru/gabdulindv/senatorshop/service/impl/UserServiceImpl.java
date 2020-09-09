package ru.gabdulindv.senatorshop.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.gabdulindv.senatorshop.model.User;
import ru.gabdulindv.senatorshop.repository.UserRepository;
import ru.gabdulindv.senatorshop.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findUserByUserDetailsDescription_PhoneContains(String phone) {
        return userRepository.findUserByUserDetailsDescription_PhoneContains(phone);
    }

    @Override
    public Optional<User> findUserByUsernameContains(String username) {
        return userRepository.findUserByUsernameContains(username);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
