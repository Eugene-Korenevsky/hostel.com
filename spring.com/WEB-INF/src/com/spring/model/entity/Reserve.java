package com.spring.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "reserve")
public class Reserve implements Serializable {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private  long id;

    public synchronized void setId(long id) {
        this.id = id;
    }

    public synchronized long getId() {
        return id;
    }

    @NotNull
    @Column(name = "DATE_IN")
    private Timestamp dateIn;

    public synchronized Timestamp getDateIn() {
        return dateIn;
    }

    public synchronized void setDateIn(Timestamp dateIn) {
        this.dateIn = dateIn;
    }

    @NotNull
    @Column(name = "DATE_OUT")
    private Timestamp dateOut;

    public synchronized Timestamp getDateOut() {
        return dateOut;
    }

    public synchronized void setDateOut(Timestamp dateOut) {
        this.dateOut = dateOut;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
    private User user;

    public synchronized User getUser() {
        return user;
    }

    public synchronized void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "ROOM_ID", referencedColumnName = "ID", nullable = false)
    private Room room;

    public synchronized Room getRoom() {
        return room;
    }

    public synchronized void setRoom(Room room) {
        this.room = room;
    }
}
