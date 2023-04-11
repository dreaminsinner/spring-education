package com.chupryna.spring.controllers;

import com.chupryna.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/userHome")
    public String userHome(ModelMap model, HttpServletRequest request) {
        model.addAttribute("ip", request.getRemoteAddr());
        String message = userService.showLoggedUserInfo();
        model.addAttribute("userInfo", message);
        return "userPages/userHome";
    }

}
