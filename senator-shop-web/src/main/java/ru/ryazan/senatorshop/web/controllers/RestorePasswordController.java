package ru.ryazan.senatorshop.web.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ryazan.senatorshop.core.domain.Customer;
import ru.ryazan.senatorshop.core.domain.User;
import ru.ryazan.senatorshop.core.mail.EmailService;
import ru.ryazan.senatorshop.core.service.CustomerService;
import ru.ryazan.senatorshop.core.service.UserService;

@Controller
public class RestorePasswordController {
    private UserService userService;
    private CustomerService customerService;
    private PasswordEncoder passwordEncoder;
    private EmailService emailService;

    public RestorePasswordController(UserService userService, CustomerService customerService, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userService = userService;
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }
    @RequestMapping("/restore-password")
    public String newPassword() {
        return "changer-password/enter-mail";
    }

    @RequestMapping(value = "/restore-password", method = RequestMethod.POST)
    public String newPassword(@RequestParam(name = "name")String name, Model model){
        User user = userService.findUserByUsername(name);
        if (user == null){
            model.addAttribute("message", "Пользователь с такой почтой не найден");
            return "changer-password/enter-mail";
        } else {
            String newPassword = getAlphaNumericString(5);
            user.setPassword(passwordEncoder.encode(newPassword));
            userService.save(user);
            Customer customer = customerService.findCustomerByCustomerName(name);
            customer.setCustomerPassword(passwordEncoder.encode(newPassword));
            customerService.addCustomer(customer);
            emailService.sendRestoreMail(name, newPassword);
            return "changer-password/success-new-password";
        }
    }

    private String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
