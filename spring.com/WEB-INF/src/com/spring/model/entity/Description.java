package com.spring.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "description")
public class Description implements Serializable {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    public long getId() {
        return id;
    }

    @NotNull
    @Column(name = "NAME")
    private String description;

    public  void setDescription(String description) {
        this.description = description;
    }

    public  String getDescription() {
        return description;
    }

    @ManyToMany(mappedBy = "descriptions")
    private Set<Room> rooms = new HashSet<>();

    public  void setRooms(Set<Room> rooms) {
            this.rooms = rooms;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room){
            rooms.add(room);
    }
}
