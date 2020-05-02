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

    public synchronized long getId() {
        return id;
    }

    public synchronized void setId(long id) {
        this.id = id;
    }

    @NotNull
    @Column(name = "NAME")
    private String description;

    public synchronized void setDescription(String description) {
        this.description = description;
    }

    public synchronized String getDescription() {
        return description;
    }

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "descriptions")
    private Set<Room> rooms = new HashSet<>();

    public synchronized void setRooms(Set<Room> rooms) {
        synchronized (this.rooms) {
            this.rooms = rooms;
        }
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room){
        synchronized (rooms){
            rooms.add(room);
        }
    }
}
