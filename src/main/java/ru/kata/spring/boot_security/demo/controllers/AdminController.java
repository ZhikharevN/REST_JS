package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String getUsers() {
        return "users";
    }

    @GetMapping("/new")
    public String newUser() {
        return "create";
    }

    @GetMapping("/getUsers")
    @ResponseBody
    public List<User> getUser() {
        return userService.show();
    }

    @GetMapping("/getUser/{id}")
    @ResponseBody
    public User getUserById(@PathVariable("id") int id) {
        return userService.getUser(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
    }

    @GetMapping("/getRoles")
    @ResponseBody
    public List<Role> getRoles() {
        return roleService.findAll();
    }

    @PatchMapping(value = "/editUser")
    @ResponseBody
    public void editUser(@ModelAttribute User user) {
        if (user.getRoles() != null) {
            user.setRoles(user.getRoles()
                    .stream()
                    .map(role -> roleService.findById(Integer.parseInt(role.getName())))
                    .collect(Collectors.toSet()));
        }
        userService.update(user.getId(), user);
    }

    @PostMapping("/create")
    @ResponseBody
    public void createUser(@ModelAttribute User user) {
        System.out.println(user);
        if (user.getRoles() != null) {
            user.setRoles(user.getRoles()
                    .stream()
                    .map(role -> roleService.findById(Integer.parseInt(role.getName())))
                    .collect(Collectors.toSet()));
        }
        userService.create(user);
    }
}
