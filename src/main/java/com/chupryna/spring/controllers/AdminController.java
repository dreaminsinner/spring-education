package com.chupryna.spring.controllers;

import com.chupryna.spring.dto.UserDto;
import com.chupryna.spring.models.Role;
import com.chupryna.spring.models.User;
import com.chupryna.spring.services.PeopleService;
import com.chupryna.spring.services.RoleService;
import com.chupryna.spring.util.Mapper;
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

    private final Mapper mapper;

    @Autowired
    public AdminController(RoleService roleService, PeopleService peopleService, UserValidator userValidator, Mapper mapper) {
        this.roleService = roleService;
        this.peopleService = peopleService;
        this.userValidator = userValidator;
        this.mapper = mapper;
    }

    @GetMapping
    private String adminHome(Model model) {
        model.addAttribute("peopleList", peopleService.findAll()
                .stream()
                .sorted(Comparator.comparing(User::getId))
                .map(mapper::userToDto)
                .collect(Collectors.toList()));
        return "adminPages/adminHome";
    }

    @GetMapping("/editUser/{id}")
    public String editUser(@PathVariable long id,
                           ModelMap model) {
        model.addAttribute("roleList", roleService.findAll());
        model.addAttribute("UserDto", mapper.userToDto(peopleService.findById(id).get()));
        return "adminPages/editUser";
    }

    @PatchMapping("/editUser/{id}")
    public String confirmEdit(
            @ModelAttribute("UserDto") @Valid UserDto userDto,
            BindingResult bindingResult,
            ModelMap model) {
        model.addAttribute("roleList", roleService.findAll());
        if (userDto.getRole().getName().equals("ROLE_ADMIN")) {
            userDto.setRole(new Role(1, "ROLE_ADMIN"));
        } else userDto.setRole(new Role(2, "ROLE_USER"));
        userValidator.validate(userDto, bindingResult);
        userValidator.checkPass(userDto.getPassword(), userDto.getPasswordConfirmation(), bindingResult);
        if (bindingResult.hasErrors()) {
            return "/adminPages/editUser";
        }
        peopleService.save(userDto);
        return "redirect:/adminHome";
    }

    @GetMapping("/addUser")
    public String addingPage(@ModelAttribute("UserDto") UserDto userDto,
                             ModelMap model) {
        model.addAttribute("roleList", roleService.findAll());
        return "adminPages/addUser";
    }

    @PostMapping("/addUser")
    public String performAdd(@ModelAttribute("UserDto") @Valid UserDto userDto,
                             BindingResult bindingResult,
                             ModelMap model) {
        model.addAttribute("roleList", roleService.findAll());
        if (userDto.getRole().getName().equals("ROLE_ADMIN")) {
            userDto.setRole(new Role(1, "ROLE_ADMIN"));
        } else userDto.setRole(new Role(2, "ROLE_USER"));
        userValidator.validate(userDto, bindingResult);
        userValidator.checkPass(userDto.getPassword(), userDto.getPasswordConfirmation(), bindingResult);
        if (bindingResult.hasErrors()) {
            return "adminPages/addUser";
        } else {
            peopleService.save(userDto);
            return "redirect:/adminHome";
        }
    }
}
