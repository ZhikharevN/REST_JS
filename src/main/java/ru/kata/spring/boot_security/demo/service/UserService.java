package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


public interface UserService {
    List<User> show();

    void delete(int id);

    void update(int id, User user);

    void create(User user);

    User getUser(int id);

    User findByUsername(String username);

    void saveAll(List<User> users);

    User findByEmail(String email);
}
