package com.chupryna.spring.controllers;

import com.chupryna.spring.dto.UserDto;
import com.chupryna.spring.services.RegistrationService;
import com.chupryna.spring.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class LoginController {

    private final UserValidator userValidator;

    private final RegistrationService registrationService;

    @Autowired
    public LoginController(UserValidator userValidator, RegistrationService registrationService) {
        this.userValidator = userValidator;
        this.registrationService = registrationService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "authorization/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("UserDto") UserDto userDto) {
        return "authorization/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("UserDto") @Valid UserDto userDto,
                                      BindingResult bindingResult) {
        userValidator.validate(userDto, bindingResult);
        userValidator.checkPass(userDto.getPassword(), userDto.getPasswordConfirmation(), bindingResult);
        if (bindingResult.hasErrors()) {
            return "authorization/registration";
        } else {
            registrationService.register(userDto);
            return "redirect:/authorization/login";
        }
    }
}
