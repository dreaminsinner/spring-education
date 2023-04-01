package com.chupryna.spring.util;


import com.chupryna.spring.models.User;
import com.chupryna.spring.services.PeopleValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class UserValidator implements Validator {

    private final PeopleValidateService peopleValidateService;

    @Autowired
    public UserValidator(PeopleValidateService peopleValidateService) {
        this.peopleValidateService = peopleValidateService;
    }

    public void checkPass(String pass, String checkPass, Errors errors) {
        if (!pass.equals(checkPass)) {
            errors.rejectValue("password", "error.user", "password error");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (peopleValidateService.isEmailExists(user.getEmail())) {
            errors.rejectValue("email", "error.user", "This email is already registered");
        }
        if (peopleValidateService.isUsernameExists(user.getUsername())) {
            errors.rejectValue("username", "error.user", "This username is already registered");
        }
    }


}
