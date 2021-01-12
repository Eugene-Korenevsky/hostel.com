package com.spring.model.dao;

import com.spring.model.entity.Role;

import javax.persistence.EntityManager;

public interface RoleDao extends GenericDao<Role> {
    public Role findByName(String name, EntityManager entityManager);
}
