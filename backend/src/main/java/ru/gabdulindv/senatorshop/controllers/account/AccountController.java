package ru.gabdulindv.senatorshop.controllers.account;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gabdulindv.senatorshop.model.User;
import ru.gabdulindv.senatorshop.model.account.FioModel;
import ru.gabdulindv.senatorshop.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {
    private UserService userService;

    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/update/fio/{id}")
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

    @RequestMapping("/update/phone/{id}")
    public ResponseEntity updatePhone(@PathVariable Long id, @RequestBody FioModel fioModel) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            user.get().getUserDetailsDescription().setPhone(fioModel.getPhone());
            userService.save(user.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping("/update/username/{id}")
    public ResponseEntity updateUsername(@PathVariable Long id, @RequestBody FioModel fioModel) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            user.get().setUsername(fioModel.getUsername());
            userService.save(user.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
