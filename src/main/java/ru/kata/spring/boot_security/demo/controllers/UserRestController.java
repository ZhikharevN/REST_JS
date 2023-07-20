package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;


@RestController
public class UserRestController {
    @GetMapping("/getUser")
    public User getUserInfo() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
