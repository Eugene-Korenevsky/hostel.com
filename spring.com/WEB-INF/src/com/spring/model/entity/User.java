package com.spring.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    public synchronized long getId() {
        return id;
    }

    @NotNull
    @Column(name = "NAME")
    private String name;

    public synchronized void setName(String name) {
        this.name = name;
    }

    public synchronized String getName() {
        return name;
    }

    @NotNull
    @Column(name = "SURNAME")
    private String surname;

    public synchronized String getSurname() {
        return surname;
    }

    public synchronized void setSurname(String surname) {
        this.surname = surname;
    }

    @NotNull
    @Column(name = "EMAIL")
    private String email;

    public synchronized String getEmail() {
        return email;
    }

    public synchronized void setEmail(String email) {
        this.email = email;
    }

    @NotNull
    @Column(name = "PASSWORD")
    private String password;

    public synchronized String getPassword() {
        return password;
    }

    public synchronized void setPassword(String password) {
        this.password = password;
    }

    @ManyToOne
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
    private Role role;

    public synchronized void setRole(Role role) {
        this.role = role;
    }

    public synchronized Role getRole() {
        return role;
    }
}
