package com.spring.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "project.order")
public class Order implements Serializable {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    public long getId() {
        return id;
    }

    @NotNull
    @Column(name = "TOTAL_PRICE")
    private double totalPrice;

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @NotNull
    @Column(name = "DATE_IN")
    private Timestamp dateIn;

    public Timestamp getDateIn() {
        return dateIn;
    }

    public void setDateIn(Timestamp dateIn) {
        this.dateIn = dateIn;
    }

    @NotNull
    @Column(name = "DATE_OUT")
    private Timestamp dateOut;

    public Timestamp getDateOut() {
        return dateOut;
    }

    public void setDateOut(Timestamp dateOut) {
        this.dateOut = dateOut;
    }

    @NotNull
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ROOM_ID", referencedColumnName = "ID")
    private Room room;

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return (order.getId() == this.getId() && order.getRoom().equals(this.getRoom()) &&
                order.getUser().equals(this.getUser()) && order.getDateIn() == this.getDateIn()
        && order.getDateOut() == this.getDateOut() && order.getTotalPrice() == this.getTotalPrice());
    }

    public int hashCode(){
        int result = 17;
        result = 37 * result + (int)id;
        result = 37 * result + user.hashCode();
        result = 37 * result + room.hashCode();
        result = 37 * result + dateIn.hashCode();
        result = 37 * result + dateOut.hashCode();
        result = 37 * result + (int) totalPrice;
        return result;
    }
}
