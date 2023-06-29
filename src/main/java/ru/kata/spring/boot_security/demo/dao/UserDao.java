package ru.kata.spring.boot_security.demo.dao;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


public interface UserDao {
    List<User> show();

    void delete(int id);

    void update(int id, User user);

    void create(User user);

    User getUser(int id);
}
