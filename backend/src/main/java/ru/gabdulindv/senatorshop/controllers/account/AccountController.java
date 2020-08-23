package ru.gabdulindv.senatorshop.controllers.account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.gabdulindv.senatorshop.model.User;
import ru.gabdulindv.senatorshop.model.account.FioModel;
import ru.gabdulindv.senatorshop.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public AccountController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value = "/update/fio/{id}", method = RequestMethod.POST)
    public ResponseEntity updateFio(@PathVariable Long id, @RequestBody FioModel fioModel) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            user.get().getUserDetailsDescription().setFIOfirst(fioModel.getFiofirst());
            user.get().getUserDetailsDescription().setFIOmiddle(fioModel.getFiomiddle());
            user.get().getUserDetailsDescription().setFIOlast(fioModel.getFiolast());
            userService.save(user.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/update/phone/{id}", method = RequestMethod.POST)
    public ResponseEntity updatePhone(@PathVariable Long id, @RequestBody FioModel fioModel) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            user.get().getUserDetailsDescription().setPhone(fioModel.getPhone());
            userService.save(user.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/update/username/{id}", method = RequestMethod.POST)
    public ResponseEntity updateUsername(@PathVariable Long id, @RequestBody FioModel fioModel) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            user.get().setUsername(fioModel.getUsername());
            userService.save(user.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/update/password/{id}", method = RequestMethod.POST)
    public ResponseEntity updatePassword(@PathVariable Long id, @RequestBody FioModel fioModel) {
        Optional<User> user = userService.findById(id);

        if (user.isPresent()) {
            String oldPass = user.get().getPassword();
            String oldPassFromUser = fioModel.getOldPassword();
            if (!passwordEncoder.matches(oldPassFromUser, oldPass)) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Старый пароль не совпадает с текущим");
            }
            user.get().setPassword(passwordEncoder.encode(oldPassFromUser));
            userService.save(user.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
