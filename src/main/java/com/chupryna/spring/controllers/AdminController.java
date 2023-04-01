package com.chupryna.spring.controllers;

import com.chupryna.spring.models.Role;
import com.chupryna.spring.models.User;
import com.chupryna.spring.services.PeopleService;
import com.chupryna.spring.services.RoleService;
import com.chupryna.spring.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/adminHome")
public class AdminController {

    private final RoleService roleService;

    private final PeopleService peopleService;

    private final UserValidator userValidator;

    @Autowired
    public AdminController(RoleService roleService, PeopleService peopleService, UserValidator userValidator) {
        this.roleService = roleService;
        this.peopleService = peopleService;
        this.userValidator = userValidator;
    }

    @GetMapping
    private String adminHome(Model model) {
        model.addAttribute("peopleList", peopleService.findAll()
                .stream()
                .sorted(Comparator.comparing(User::getId))
                .collect(Collectors.toList()));
        return "adminPages/adminHome";
    }

    @GetMapping("/editUser/{id}")
    public String editUser(@PathVariable long id,
                           ModelMap model) {
        model.addAttribute("roleList", roleService.findAll());
        model.addAttribute("user", peopleService.findById(id));
        return "adminPages/editUser";
    }

    @PatchMapping("/editUser/{id}")
    public String confirmEdit(
            @ModelAttribute("user") @Valid User user,
            BindingResult bindingResult,
            ModelMap model) {
        model.addAttribute("roleList", roleService.findAll());
        String passConfirm = (String) model.getAttribute("passConfirm");
        if(user.getRole().getName().equals("ROLE_ADMIN")){
            user.setRole(new Role(1,"ROLE_ADMIN"));
        } else user.setRole(new Role(2,"ROLE_USER"));
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            System.out.println("ZALETEL");
            return "/adminPages/editUser";
        }
            peopleService.save(user);
            return "redirect:/adminHome";
    }
}
