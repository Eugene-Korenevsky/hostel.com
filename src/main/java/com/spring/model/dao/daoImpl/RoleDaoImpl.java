package com.spring.model.dao.daoImpl;

import com.spring.model.dao.RoleDao;
import com.spring.model.entity.Role;


public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {
    RoleDaoImpl() {
        super(Role.class);
    }
}
