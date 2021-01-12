package com.spring.model.dao;

import com.spring.model.entity.Room;

import javax.persistence.EntityManager;

public interface RoomDao extends GenericDao<Room> {
    public Room findByRoomNumber(int roomNumber, EntityManager entityManager);

}
