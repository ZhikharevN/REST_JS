package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public class RoleRepositoryImp implements RoleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> findAll() {
        return entityManager.createQuery("SELECT role FROM Role role").getResultList();
    }


    @Override
    @Transactional
    public void saveAll(Set<Role> roles) {
        roles.stream().forEach(role -> entityManager.persist(role));
    }

    @Override
    public Role findById(int id) {
        return entityManager.find(Role.class, id);
    }
}
