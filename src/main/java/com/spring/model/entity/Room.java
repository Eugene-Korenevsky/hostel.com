package com.spring.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "room")
public class Room implements Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    public long getId() {
        return id;
    }

    @NotNull
    @Column(name = "NUMBER")
    private int number;

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @NotNull
    @Column(name = "CLASS")
    private String roomClass;

    public String getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }

    @NotNull
    @Column(name = "SITS")
    private Integer sits;

    public Integer getSits() {
        return sits;
    }

    public void setSits(Integer sits) {
        this.sits = sits;
    }

    @NotNull
    @Column(name = "PRICE")
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "room_description",
            joinColumns = @JoinColumn(name = "ROOM_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "DESCRIPTION_ID", referencedColumnName = "ID"))
    private Set<Description> descriptions = new HashSet<>();

    public Set<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Set<Description> descriptions) {
        this.descriptions = descriptions;
    }

    public void addDescription(Description description) {
        descriptions.add(description);
    }

    public void removeDescription(Description description) {
        descriptions.remove(description);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return (room.getId() == this.getId() && room.getNumber() == this.getNumber()
                && room.getPrice() == this.getPrice() && room.getSits() == this.getSits()
                && room.getRoomClass().equals(this.getRoomClass()));
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + (int) id;
        result = 37 * result + number;
        result = 37 * result + (int) price;
        result = 37 * result + sits;
        result = 37 * result + roomClass.hashCode();
        return result;
    }
}
