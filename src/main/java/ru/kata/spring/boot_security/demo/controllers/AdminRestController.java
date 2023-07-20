package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminRestController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userService.show();
    }

    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable("id") int id) {
        return userService.getUser(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
    }

    @GetMapping("/getRoles")
    public List<Role> getRoles() {
        return roleService.findAll();
    }

    @PatchMapping(value = "/editUser")
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


