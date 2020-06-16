package com.spring.model.dao;

import com.spring.model.entity.Reserve;

import javax.persistence.EntityManager;
import java.util.List;

public interface ReserveDao extends GenericDao<Reserve> {
    List<Reserve> findAllByUserId(long id, EntityManager entityManager);
}
