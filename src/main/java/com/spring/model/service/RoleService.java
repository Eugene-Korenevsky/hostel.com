package com.spring.model.service;

import com.spring.model.entity.Role;

import java.util.List;

public interface RoleService {
    public List<Role> readAll();

    public Role findById(long id);

    public void create(Role role);

    public void update(Role role);

    public void delete(long roleId);
}
