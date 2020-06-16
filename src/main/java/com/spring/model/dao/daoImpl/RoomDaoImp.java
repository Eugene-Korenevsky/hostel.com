package com.spring.model.dao.daoImpl;

import com.spring.model.dao.GenericDao;
import com.spring.model.entity.Room;

import javax.persistence.EntityManager;
import java.util.List;

public class RoomDao extends GenericDaoImpl<Room> {
    RoomDao(){
        super(Room.class);
    }
}
