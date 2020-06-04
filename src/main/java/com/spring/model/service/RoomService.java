package com.spring.model.service;

import com.spring.model.entity.Room;

import java.util.List;

public interface RoomService {

    public Room readById(long id);

    public List<Room> readAll();

    public void create(double price,String roomClass,int sits, int number);

    public void update(long roomId,double price,String roomClass,int sits, int number);

    public void delete(long id);

    public void removeDescription(long roomId,long descriptionId);

    public void addDescription(long roomId,long descriptionId);
}
