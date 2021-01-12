package com.spring.model.dao.daoImpl;

import com.spring.model.dao.GenericDao;
import com.spring.model.dao.ReserveDao;
import com.spring.model.entity.Order;
import com.spring.model.entity.Reserve;
import com.spring.model.entity.Room;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.List;

public class ReserveDaoImpl extends GenericDaoImpl<Reserve> implements ReserveDao {
    ReserveDaoImpl() {
        super(Reserve.class);
    }

    @Override
    public List<Reserve> findAllByUserId(long id, EntityManager entityManager) {
        TypedQuery<Reserve> query =
                entityManager.createNamedQuery("findReservesByUserId", Reserve.class).setParameter("USER_ID", id);
        return query.getResultList();
    }

    @Override
    public List<Reserve> findByDatesInterval(Timestamp dateIn, Timestamp dateOut, Room room, EntityManager entityManager) {
        CriteriaQuery<Reserve> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Reserve.class);
        Root<Reserve> itemRoot = criteriaQuery.from(Reserve.class);
        Predicate predicateDateIn = entityManager.getCriteriaBuilder().between(
                itemRoot.<Timestamp>get("dateIn"),
                dateIn, dateOut);
        Predicate predicateDateOut = entityManager.getCriteriaBuilder().between(
                itemRoot.<Timestamp>get("dateOut"),
                dateIn, dateOut);
        Predicate predicateOrDates = entityManager.getCriteriaBuilder().or(
                predicateDateIn, predicateDateOut
        );
        Predicate predicateRoom = entityManager.getCriteriaBuilder().equal(
                itemRoot.<Long>get("room"), room
        );
        Predicate predicate = entityManager.getCriteriaBuilder().and(
                predicateOrDates, predicateRoom
        );
        criteriaQuery.where(predicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}

