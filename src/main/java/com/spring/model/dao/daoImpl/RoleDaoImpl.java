package com.spring.model.dao.daoImpl;

import com.spring.model.dao.RoleDao;
import com.spring.model.entity.Description;
import com.spring.model.entity.Role;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {
    RoleDaoImpl() {
        super(Role.class);
    }

    @Override
    public Role findByName(String name, EntityManager entityManager) {
        TypedQuery<Role> query =
                entityManager.createNamedQuery("findRoleByName", Role.class);
        query.setParameter("role", name);
        try {
            return query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
}
