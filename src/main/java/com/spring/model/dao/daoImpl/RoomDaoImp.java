package com.spring.model.dao.daoImpl;

import com.spring.model.dao.GenericDao;
import com.spring.model.dao.RoomDao;
import com.spring.model.entity.Room;

import javax.persistence.EntityManager;
import java.util.List;

public class RoomDaoImp extends GenericDaoImpl<Room> implements RoomDao {
    RoomDaoImp(){
        super(Room.class);
    }
}
