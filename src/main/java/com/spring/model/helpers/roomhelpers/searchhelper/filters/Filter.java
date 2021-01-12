package com.spring.model.helpers.roomhelpers.searchhelper.filters;

import com.spring.model.service.exceptions.ValidationException;

public interface Filter<T,T1> {
    public   boolean doFilter(T entity,T1 param) throws ValidationException;
}
