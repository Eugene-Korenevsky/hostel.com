package com.spring.model.service;

import com.spring.model.entity.Reserve;
import com.spring.model.entity.Room;
import com.spring.model.service.exceptions.EntityNotFoundException;
import com.spring.model.service.exceptions.ReserveServiceException;
import com.spring.model.service.exceptions.ValidationException;

import java.sql.Timestamp;
import java.util.List;

public interface ReserveService extends GenericService<Reserve> {

    public List<Reserve> readAllByUserId(long userId) throws ReserveServiceException;

    public Reserve create(long orderId) throws ReserveServiceException, EntityNotFoundException;

    public List<Reserve> findByDatesIntervalAndRoom(Timestamp dateIn, Timestamp dateOut, Room room)
            throws ReserveServiceException,ValidationException;
}
