package com.spring.model.service;

import com.spring.model.entity.Description;

import java.util.List;

public interface DescriptionService {
    public Description findById(long id);

    public List<Description> readAll();

    public void delete(long id);

    public void update(Description description);

    public void create(String name);

}
