package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kata.spring.boot_security.demo.model.User;

@Controller
public class UserController {
    @GetMapping("/user")
    public String getInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/getUser")
    @ResponseBody
    public User getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User)authentication.getPrincipal();
    }
}
