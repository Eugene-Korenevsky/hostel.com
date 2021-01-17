package com.spring.model.service;

import com.spring.model.entity.User;
import com.spring.model.service.exceptions.EmailIsExistException;
import com.spring.model.service.exceptions.EntityNotFoundException;
import com.spring.model.service.exceptions.UserServiceException;
import com.spring.model.service.exceptions.ValidationException;

import java.util.List;

public interface UserService extends GenericService<User> {

    public User login(String email,String password) throws UserServiceException,ValidationException;

    public User register(User user) throws EmailIsExistException, UserServiceException;

    public User changeUserRole(long userId,long roleId) throws UserServiceException,EntityNotFoundException;
}
