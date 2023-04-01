package com.chupryna.spring.controllers;

import com.chupryna.spring.models.User;
import com.chupryna.spring.services.RegistrationService;
import com.chupryna.spring.services.RoleService;
import com.chupryna.spring.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
    public String loginPage(){
        return "authorization/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user")User user){
        return "authorization/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid User user,
                                      BindingResult bindingResult,
                                      ModelMap model){
        System.out.println(model.getAttribute("passConfirm"));
        String passConfirm = (String) model.getAttribute("passConfirm");
        userValidator.validate(user, bindingResult);
        userValidator.checkPass(user.getPassword(), passConfirm, bindingResult);
        if(bindingResult.hasErrors()) {
            return "authorization/registration";
        } else
            {
                registrationService.register(user);
                return "redirect:/authorization/login";
            }
    }
}
