package com.spring.model.service;

import com.spring.model.entity.Role;
import com.spring.model.service.exceptions.EntityNotFoundException;
import com.spring.model.service.exceptions.RoleServiceException;
import com.spring.model.service.exceptions.ValidationException;

import java.util.List;

public interface RoleService extends GenericService<Role> {
}
