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

    public  long getId() {
        return id;
    }

    @NotNull
    @Column(name = "DATE_IN")
    private Timestamp dateIn;

    public  Timestamp getDateIn() {
        return dateIn;
    }

    public  void setDateIn(Timestamp dateIn) {
        this.dateIn = dateIn;
    }

    @NotNull
    @Column(name = "DATE_OUT")
    private Timestamp dateOut;

    public  Timestamp getDateOut() {
        return dateOut;
    }

    public  void setDateOut(Timestamp dateOut) {
        this.dateOut = dateOut;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID", referencedColumnName = "ID")
    private Room room;

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}