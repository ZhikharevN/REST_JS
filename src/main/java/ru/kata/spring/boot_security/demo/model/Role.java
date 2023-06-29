package ru.kata.spring.boot_security.demo.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {
    @Override
    public String toString() {
        return this.name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "role_name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "roles")
    private List<User> users;

    public Role() {

    }

    public Role(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


}
