package com.spring.model.service;

import com.spring.model.entity.Description;
import com.spring.model.service.exceptions.DescriptionServiceException;
import com.spring.model.service.exceptions.EntityNotFoundException;
import com.spring.model.service.exceptions.ValidationException;

import java.util.List;

public interface DescriptionService extends GenericService<Description> {
    public Description findById(long id,boolean withRooms) throws DescriptionServiceException, EntityNotFoundException;

    public List<Description> readAll(boolean withRooms) throws DescriptionServiceException;

    public Description create(String name) throws DescriptionServiceException,ValidationException;

}
