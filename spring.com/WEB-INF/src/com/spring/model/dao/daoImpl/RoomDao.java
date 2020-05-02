package com.spring.model.dao.daoImpl;

import com.spring.model.dao.GenericDao;
import com.spring.model.entity.Room;

import javax.persistence.EntityManager;
import java.util.List;

public class RoomDao extends GenericDao<Room> {
    @Override
    public Room findById(long id, EntityManager entityManager) {
        return entityManager.find(Room.class, id);
    }

    @Override
    public List<Room> readAll(EntityManager entityManager) {
        return entityManager.createQuery("select i from Room i").getResultList();
    }

    @Override
    public void delete(Room entity,EntityManager entityManager) {
        entityManager.remove(entity);
    }

    @Override
    public void update(Room entity,EntityManager entityManager) {
        if (entityManager.contains(entity)) entityManager.persist(entity);
        else entityManager.merge(entity);
    }

    @Override
    public void create(Room entity,EntityManager entityManager) {
        entityManager.persist(entity);
    }

    @Override
    public Room createEntity(Room entity, EntityManager entityManager) {
        return entityManager.merge(entity);
    }

    @Override
    public Room updateEntity(Room entity, EntityManager entityManager) {
        return entityManager.merge(entity);
    }
}
