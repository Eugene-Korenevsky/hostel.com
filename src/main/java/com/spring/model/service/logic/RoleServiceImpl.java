package com.spring.model.service.logic;

import com.spring.model.dao.RoleDao;
import com.spring.model.entity.Role;
import com.spring.model.entitymanager.EntityManagerFactory;
import com.spring.model.service.RoleService;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> readAll() {
        List<Role> roles = new ArrayList<>();
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            roles = roleDao.readAll(entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return roles;
    }

    @Override
    public Role findById(long id) {
        Role role = new Role();
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            role = roleDao.findById(id, entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return role;
    }

    @Override
    public void create(Role role) {
        if (role != null) {
            EntityManager entityManager = EntityManagerFactory.getEntityManager();
            try {
                entityManager.getTransaction().begin();
                roleDao.create(role, entityManager);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
            } finally {
                entityManager.close();
            }
        }
    }

    @Override
    public void update(Role role) {
        if (role != null) {
            EntityManager entityManager = EntityManagerFactory.getEntityManager();
            try {
                entityManager.getTransaction().begin();
                roleDao.update(role, entityManager);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
            } finally {
                entityManager.close();
            }
        }
    }

    @Override
    public void delete(long roleId) {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Role role = roleDao.findById(roleId, entityManager);
            roleDao.delete(role, entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
}

