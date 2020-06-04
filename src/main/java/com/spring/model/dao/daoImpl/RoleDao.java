package com.spring.model.dao.daoImpl;

import com.spring.model.dao.GenericDao;
import com.spring.model.entity.Role;

import javax.persistence.EntityManager;
import java.util.List;

public class RoleDao extends GenericDao<Role> {
    @Override
    public Role findById(long id, EntityManager entityManager) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public List<Role> readAll(EntityManager entityManager) {
        return entityManager.createQuery("select i from Role i").getResultList();
    }

    @Override
    public void delete(Role entity,EntityManager entityManager) {
        entityManager.remove(entity);
    }

    @Override
    public void update(Role entity,EntityManager entityManager) {
        if (entityManager.contains(entity))entityManager.persist(entity);
        else entityManager.merge(entity);
    }

    @Override
    public void create(Role entity,EntityManager entityManager) {
        entityManager.persist(entity);
    }

    @Override
    public Role createEntity(Role entity, EntityManager entityManager) {
        return entityManager.merge(entity);
    }

    @Override
    public Role updateEntity(Role entity, EntityManager entityManager) {
        return entityManager.merge(entity);
    }
}
