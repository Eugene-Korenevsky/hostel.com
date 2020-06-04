package com.spring.model.service.logic;

import com.spring.model.dao.daoImpl.DescriptionDao;
import com.spring.model.entity.Description;
import com.spring.model.entity.Room;
import com.spring.model.entitymanager.EntityManagerFactory;
import com.spring.model.service.BaseService;
import com.spring.model.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl extends BaseService implements RoomService {
    @Autowired
    private DescriptionDao descriptionDao;

    public void setDescriptionDao(DescriptionDao descriptionDao) {
        this.descriptionDao = descriptionDao;
    }

    @Override
    public Room readById(long id) {
        Room room = new Room();
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            room = (Room) getGenericDao().findById(id, entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return room;
    }

    @Override
    public List<Room> readAll() {
        List<Room> rooms = new ArrayList<>();
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            rooms = getGenericDao().readAll(entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return rooms;
    }

    @Override
    public void create(double price, String roomClass, int sits, int number) {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        Room room = new Room();
        room.setNumber(number);
        room.setPrice(price);
        room.setRoomClass(roomClass);
        room.setSits(sits);
        try {
            entityManager.getTransaction().begin();
            getGenericDao().create(room, entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void update(long roomId, double price, String roomClass, int sits, int number) {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Room room = (Room) getGenericDao().findById(roomId, entityManager);
            if (room != null) {
                room.setNumber(number);
                room.setPrice(price);
                room.setRoomClass(roomClass);
                room.setSits(sits);
                getGenericDao().update(room, entityManager);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }

    }

    @Override
    public void delete(long id) {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Room room = (Room) getGenericDao().findById(id, entityManager);
            if (room != null) {
                getGenericDao().delete(room, entityManager);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void removeDescription(long roomId, long descriptionId) {
        if (roomId != 0 && descriptionId != 0) {
            EntityManager entityManager = EntityManagerFactory.getEntityManager();
            try {
                entityManager.getTransaction().begin();
                Room room = (Room) getGenericDao().findById(roomId, entityManager);
                if (room != null) {
                    room.removeDescription(descriptionId);
                    getGenericDao().update(room, entityManager);
                }
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    @Override
    public void addDescription(long roomId, long descriptionId) {
        if (roomId != 0 && descriptionId != 0) {
            EntityManager entityManager = EntityManagerFactory.getEntityManager();
            try {
                entityManager.getTransaction().begin();
                Description description = descriptionDao.findById(descriptionId, entityManager);
                Room room = (Room) getGenericDao().findById(roomId, entityManager);
                if (room != null && description != null) {
                    room.addDescription(description);
                    getGenericDao().update(room, entityManager);
                }
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
            }
        }
    }
}
