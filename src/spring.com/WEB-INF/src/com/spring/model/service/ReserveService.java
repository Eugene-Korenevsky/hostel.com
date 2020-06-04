package com.spring.model.service;

import com.spring.model.entity.Reserve;

import java.util.List;

public interface ReserveService {
    public Reserve findById(long id);

    public List<Reserve> readAll();

    public List<Reserve> readAllByUserId(long userId);

    public void create(long orderId);

    public void update(Reserve reserve);

    public void delete(long id);
}
