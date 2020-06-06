package com.spring.model.service.logic;

import com.spring.model.entity.Description;
import com.spring.model.entitymanager.EntityManagerFactory;
import com.spring.model.service.BaseService;
import com.spring.model.service.DescriptionService;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class DescriptionServiceImpl extends BaseService implements DescriptionService {
    @Override
    public Description findById(long id,boolean withRooms) {
        Description description = new Description();
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            description = (Description) getGenericDao().findById(id,entityManager);
            if (withRooms)description.getRooms().size();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }finally {
            entityManager.close();
        }
        return description;
    }

    @Override
    public List<Description> readAll(boolean withRooms) {
        List<Description> descriptions = new ArrayList<>();
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            descriptions = getGenericDao().readAll(entityManager);
            if (withRooms){
                for (Description description : descriptions){
                    description.getRooms().size();
                }
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }finally {
            entityManager.close();
        }
        return descriptions;
    }

    @Override
    public void delete(long id) {
            EntityManager entityManager = EntityManagerFactory.getEntityManager();
            try {
                entityManager.getTransaction().begin();
                Description description = (Description) getGenericDao().findById(id,entityManager);
                if (description != null){
                    getGenericDao().delete(description,entityManager);
                }
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
            }finally {
                entityManager.close();
            }
    }

    @Override
    public void update(Description description) {
        if (description != null) {
            EntityManager entityManager = EntityManagerFactory.getEntityManager();
            try {
                entityManager.getTransaction().begin();
                getGenericDao().update(description,entityManager);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
            }finally {
                entityManager.close();
            }
        }
    }

    @Override
    public void create(String name) {
            EntityManager entityManager = EntityManagerFactory.getEntityManager();
            Description description = new Description();
            description.setDescription(name);
            try {
                entityManager.getTransaction().begin();
                getGenericDao().create(description,entityManager);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
            }finally {
                entityManager.close();
            }
    }
}
