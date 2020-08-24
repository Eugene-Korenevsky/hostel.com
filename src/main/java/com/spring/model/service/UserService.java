package com.spring.model.service;

import com.spring.model.entity.User;
import com.spring.model.service.exceptions.EmailIsExistException;

import java.util.List;

public interface UserService {
    public User findById(long id);

    public List<User> readAll();

    public void create(User user);

    public void update(User user);

    public void delete(long userId);

    public User login(String email,String password);

    public User register(User user) throws EmailIsExistException;

    public void changeUserRole(long userId,long roleId);
}
