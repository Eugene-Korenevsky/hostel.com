package com.spring.model.service;

import com.spring.model.entity.Role;
import com.spring.model.service.exceptions.EntityNotFoundException;
import com.spring.model.service.exceptions.RoleServiceException;
import com.spring.model.service.exceptions.ValidationException;

import java.util.List;

public interface RoleService {
    public List<Role> readAll() throws RoleServiceException;

    public Role findById(long id) throws RoleServiceException, EntityNotFoundException;

    public Role create(Role role) throws RoleServiceException, ValidationException;

    public Role update(Role role) throws RoleServiceException,ValidationException,EntityNotFoundException;

    public void delete(long roleId) throws RoleServiceException,EntityNotFoundException;
}
