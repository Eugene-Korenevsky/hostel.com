package com.spring.model.dao;

import com.spring.model.entity.Reserve;
import com.spring.model.entity.Room;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.List;

public interface ReserveDao extends GenericDao<Reserve> {
    List<Reserve> findAllByUserId(long id, EntityManager entityManager);

    List<Reserve> findByDatesInterval(Timestamp dateIn, Timestamp dateOut, Room room, EntityManager entityManager);
}
