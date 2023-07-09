package ru.kata.spring.boot_security.demo.dao;


import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;


public interface RoleRepository {
    List<Role> findAll();

    void saveAll(Set<Role> roles);

    Role findById(int id);
}
