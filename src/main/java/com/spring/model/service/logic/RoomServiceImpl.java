package com.spring.model.service.logic;

import com.spring.model.dao.DescriptionDao;
import com.spring.model.dao.RoomDao;
import com.spring.model.entity.Description;
import com.spring.model.entity.Room;
import com.spring.model.entitymanager.EntityManagerFactory;
import com.spring.model.service.RoomService;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


public class RoomServiceImpl implements RoomService {

    private DescriptionDao descriptionDao;

    private RoomDao roomDao;

    public void setRoomDao(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    public void setDescriptionDao(DescriptionDao descriptionDao) {
        this.descriptionDao = descriptionDao;
    }

    @Override
    public Room readById(long id, boolean withDesc) {
        Room room = new Room();
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            room = roomDao.findById(id, entityManager);
            if (withDesc) room.getDescriptions().size();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return room;
    }

    @Override
    public List<Room> readAll(boolean withDesc) {
        List<Room> rooms = new ArrayList<>();
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            rooms = roomDao.readAll(entityManager);
            if (withDesc) {
                for (Room room : rooms) {
                    room.getDescriptions().size();
                }
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("error");
            System.out.println("--------------");
            System.out.println(e.getMessage());
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            entityManager.close();
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
            roomDao.create(room, entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void createRoom(Room room) {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            roomDao.create(room, entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void update(long roomId, double price, String roomClass, int sits, int number) {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Room room = roomDao.findById(roomId, entityManager);
            if (room != null) {
                room.setNumber(number);
                room.setPrice(price);
                room.setRoomClass(roomClass);
                room.setSits(sits);
                roomDao.update(room, entityManager);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

    }

    @Override
    public void delete(long id) {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Room room = roomDao.findById(id, entityManager);
            if (room != null) {
                roomDao.delete(room, entityManager);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void removeDescription(long roomId, long descriptionId) {
        if (roomId != 0 && descriptionId != 0) {
            EntityManager entityManager = EntityManagerFactory.getEntityManager();
            try {
                entityManager.getTransaction().begin();
                Room room = roomDao.findById(roomId, entityManager);
                Description description = descriptionDao.findById(descriptionId, entityManager);
                if (room != null) {
                    room.removeDescription(description);
                    roomDao.update(room, entityManager);
                }
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
            } finally {
                entityManager.close();
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
                Room room = roomDao.findById(roomId, entityManager);
                if (room != null && description != null) {
                    room.addDescription(description);
                    roomDao.update(room, entityManager);
                }
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    @Override
    public void update(Room room) {
        EntityManager entityManager = EntityManagerFactory.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            roomDao.update(room, entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
}
