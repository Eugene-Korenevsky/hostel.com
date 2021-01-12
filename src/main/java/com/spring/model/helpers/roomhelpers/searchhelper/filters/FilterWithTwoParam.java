package com.spring.model.helpers.roomhelpers.searchhelper.filters;

import com.spring.model.service.exceptions.ValidationException;

public interface FilterWithTwoParam<T,T1,T2>  {
    public boolean doFilter(T entity,T1 param1,T2 param2) throws ValidationException;
}
