package com.spring.model.service;

import com.spring.model.service.exceptions.EntityNotFoundException;
import com.spring.model.service.exceptions.ServiceException;
import com.spring.model.service.exceptions.ValidationException;

import java.util.List;

public interface GenericService<T> {
    public T findById(long id)throws ServiceException, EntityNotFoundException;

    public List<T> readAll() throws ServiceException;

    public void delete(long id) throws EntityNotFoundException,ServiceException;

    public T create(T entity) throws ValidationException,ServiceException;

    public T update(T entity,long id) throws ValidationException,ServiceException,EntityNotFoundException;


}
