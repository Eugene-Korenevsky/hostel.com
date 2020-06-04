package com.spring.model.service;

import com.spring.model.entity.User;

import java.util.List;

public interface UserService {
    public User findById(long id);

    public List<User> readAll();

    public void create(User user);

    public void update(User user);

    public void delete(User user);

    public User login(String email,String password);

    public User register(String email,String password,String name,String surname);

    public void changeUserRole(long userId,long roleId);
}
