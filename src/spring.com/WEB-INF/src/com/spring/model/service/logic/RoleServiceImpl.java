package com.spring.model.service.logic;

import com.spring.model.dao.GenericDao;
import com.spring.model.entity.Role;
import com.spring.model.entitymanager.EntityManagerFactory;
import com.spring.model.service.BaseService;
import com.spring.model.service.RoleService;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class RoleServiceImpl extends BaseService implements RoleService {


    @Override
    public List<Role> readAll() {
        List<Role> roles = new ArrayList<>();
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            //getGenericDao().getEntityManager().getTransaction().begin();
            roles = getGenericDao().readAll(entityManager);
            //getGenericDao().getEntityManager().getTransaction().commit();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
           // getGenericDao().getEntityManager().getTransaction().rollback();
        }
        return roles;
    }

    @Override
    public Role findById(long id) {
        Role role = new Role();
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            //getGenericDao().getEntityManager().getTransaction().begin();
            role = (Role) getGenericDao().findById(id,entityManager);
            entityManager.getTransaction().commit();
            //getGenericDao().getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
           // getGenericDao().getEntityManager().getTransaction().rollback();
        }
        return role;
    }

    @Override
    public void create(Role role) {
        if (role != null) {
            EntityManager entityManager = EntityManagerFactory.getEntityManager();
            try {
                entityManager.getTransaction().begin();
                //getGenericDao().getEntityManager().getTransaction().begin();
                getGenericDao().create(role,entityManager);
                entityManager.getTransaction().commit();
               // getGenericDao().getEntityManager().getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                //getGenericDao().getEntityManager().getTransaction().rollback();
            }
        }
    }

    @Override
    public void update(Role role) {
        if (role != null) {
            EntityManager entityManager = EntityManagerFactory.getEntityManager();
            try {
                entityManager.getTransaction().begin();
               // getGenericDao().getEntityManager().getTransaction().begin();
                getGenericDao().update(role,entityManager);
                entityManager.getTransaction().commit();
               // getGenericDao().getEntityManager().getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
               // getGenericDao().getEntityManager().getTransaction().rollback();
            }
        }
    }

    @Override
    public void delete(Role role) {
        if (role != null) {
            EntityManager entityManager = EntityManagerFactory.getEntityManager();
            try {
                entityManager.getTransaction().begin();
               // getGenericDao().getEntityManager().getTransaction().begin();
                getGenericDao().delete(role,entityManager);
               // getGenericDao().getEntityManager().getTransaction().commit();
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                //getGenericDao().getEntityManager().getTransaction().rollback();
            }
        }
    }
}
