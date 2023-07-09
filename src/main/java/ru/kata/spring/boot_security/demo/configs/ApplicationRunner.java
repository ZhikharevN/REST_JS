package ru.kata.spring.boot_security.demo.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Set;


@Component
public class ApplicationRunner implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;

    public ApplicationRunner(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;


    }

    @Override
    public void run(String... args) throws Exception {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");

        roleService.saveAll(Set.of(roleAdmin, roleUser));

        User Nikita = new User("Nikita", "Zhikharev", 11, "1111", "zhikharev@mail.ru", Set.of(roleAdmin));
        User Elena = new User("Elena", "Smith", 22, "2222", "Smith@mail.ru", Set.of(roleUser));
        User John = new User("John", "Adams", 33, "3333", "Adams@mail.ru", Set.of(roleUser, roleAdmin));

        userService.saveAll(List.of(Nikita, Elena, John));
    }
}
