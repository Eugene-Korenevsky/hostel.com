package com.spring.model.service;

import com.spring.model.entity.User;
import com.spring.model.service.exceptions.EmailIsExistException;
import com.spring.model.service.exceptions.EntityNotFoundException;
import com.spring.model.service.exceptions.UserServiceException;
import com.spring.model.service.exceptions.ValidationException;

import java.util.List;

public interface UserService {
    public User findById(long id) throws UserServiceException, EntityNotFoundException;

    public List<User> readAll() throws UserServiceException;

    public User create(User user) throws UserServiceException, ValidationException;

    public User update(User user) throws UserServiceException,EntityNotFoundException,ValidationException;

    public void delete(long userId) throws UserServiceException,EntityNotFoundException;

    public User login(String email,String password) throws UserServiceException,ValidationException;

    public User register(User user) throws EmailIsExistException, UserServiceException;

    public User changeUserRole(long userId,long roleId) throws UserServiceException,EntityNotFoundException;
}
