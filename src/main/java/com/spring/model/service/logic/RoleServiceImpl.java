package com.spring.model.service.logic;

import com.spring.model.aspects.MyLogger;
import com.spring.model.dao.RoleDao;
import com.spring.model.entity.Role;
import com.spring.model.entity.ValidError;
import com.spring.model.entitymanager.EntityManagerFactory;
import com.spring.model.helpers.validator.MyValidator;
import com.spring.model.service.RoleService;
import com.spring.model.service.exceptions.EntityNotFoundException;
import com.spring.model.service.exceptions.RoleServiceException;
import com.spring.model.service.exceptions.ValidationException;

import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RoleServiceImpl extends GenericServiceImpl<Role> implements RoleService {
    private RoleDao roleDao;

    public RoleServiceImpl(){
        super(Role.class);
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

}

