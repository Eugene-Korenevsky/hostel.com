package com.spring.model.dao.daoImpl;

import com.spring.model.dao.GenericDao;
import com.spring.model.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.List;

public class UserDao extends GenericDaoImpl<User> {
    UserDao(){super(User.class);}
}
