package ru.kata.spring.boot_security.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import ru.kata.spring.boot_security.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String userPage(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();
        if (!(principal instanceof UserDetails)) {
            throw new RuntimeException("Unexpected principal type");
        }
        UserDetails userDetails = (UserDetails) principal;
        model.addAttribute("user", userDetails);
        return "user";
    }
}