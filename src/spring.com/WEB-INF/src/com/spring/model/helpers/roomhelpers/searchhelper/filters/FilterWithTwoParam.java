package com.spring.model.helpers.roomhelpers.searchhelper.filters;

public interface FilterWithTwoParam<T,T1,T2>  {
    public boolean doFilter(T entity,T1 param1,T2 param2);
}
