package com.spring.model.service;

import com.spring.model.entity.Description;
import com.spring.model.entity.Room;
import com.spring.model.entity.RoomForm;
import com.spring.model.service.exceptions.EntityNotFoundException;
import com.spring.model.service.exceptions.RoomServiceException;
import com.spring.model.service.exceptions.RoomWithThisNumberIsExist;
import com.spring.model.service.exceptions.ValidationException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface RoomService {

    public Room readById(long id, boolean withDesc) throws RoomServiceException, EntityNotFoundException;

    public List<Room> readAll(boolean withDesc) throws RoomServiceException;

    public Room createRoom(RoomForm roomForm) throws RoomServiceException, ValidationException, RoomWithThisNumberIsExist;

    public void delete(long id) throws RoomServiceException,EntityNotFoundException;

    public Room update(RoomForm roomForm,long roomId) throws RoomServiceException,EntityNotFoundException,ValidationException;
}
