package com.spring.model.dao.daoImpl;

import com.spring.model.dao.GenericDao;
import com.spring.model.dao.RoomDao;
import com.spring.model.entity.Description;
import com.spring.model.entity.Room;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class RoomDaoImp extends GenericDaoImpl<Room> implements RoomDao {
    RoomDaoImp() {
        super(Room.class);
    }

    @Override
    public Room findByRoomNumber(int roomNumber, EntityManager entityManager) {
        TypedQuery<Room> query =
                entityManager.createNamedQuery("findRoomByNumber", Room.class);
        query.setParameter("number", roomNumber);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
