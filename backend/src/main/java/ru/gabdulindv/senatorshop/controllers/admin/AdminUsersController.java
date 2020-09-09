package ru.gabdulindv.senatorshop.controllers.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gabdulindv.senatorshop.model.User;
import ru.gabdulindv.senatorshop.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminUsersController {
    private UserService userService;

    public AdminUsersController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/account/all")
    public ResponseEntity findAllUsers(@RequestParam(value = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 8, Sort.by("id").descending());
        Page<User> users = userService.findAll(pageable);
        return ResponseEntity.ok(users);
    }

    @RequestMapping(value = "/account/delete/{id}", method = RequestMethod.POST)
    public ResponseEntity deleteUserById(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok("User has been deleted");
    }

    @RequestMapping("/account/{id}")
    public ResponseEntity findUserById(@PathVariable("id") Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.notFound().build();
    }
}
