package com.spring.model.dao.daoImpl;

import com.spring.model.dao.GenericDao;
import com.spring.model.entity.Role;

import javax.persistence.EntityManager;
import java.util.List;

public class RoleDao extends GenericDaoImpl<Role> {
    RoleDao(){super(Role.class);}
}
