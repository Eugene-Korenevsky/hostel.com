package com.spring.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "room")
public class Room implements Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    public synchronized void setId(long id) {
        this.id = id;
    }

    public synchronized long getId() {
        return id;
    }

    @NotNull
    @Column(name = "NUMBER")
    private int number;

    public synchronized void setNumber(int number) {
        this.number = number;
    }

    public synchronized int getNumber() {
        return number;
    }

    @NotNull
    @Column(name = "CLASS")
    private String roomClass;

    public synchronized String getRoomClass() {
        return roomClass;
    }

    public synchronized void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }

    @NotNull
    @Column(name = "SITS")
    private Integer sits;

    public synchronized Integer getSits() {
        return sits;
    }

    public synchronized void setSits(Integer sits) {
        this.sits = sits;
    }

    @NotNull
    @Column(name = "PRICE")
    private Double price;

    public synchronized Double getPrice() {
        return price;
    }

    public synchronized void setPrice(Double price) {
        this.price = price;
    }

    @ManyToMany
    @JoinTable(name = "room_description",
            joinColumns = @JoinColumn(name = "ROOM_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "DESCRIPTION_ID", referencedColumnName = "ID"))
    private  Set<Description> descriptions;

    public  Set<Description> getDescriptions() {
        return descriptions;
    }

    public synchronized void  setDescriptions(Set<Description> descriptions) {
        synchronized (this.descriptions) {
            this.descriptions = descriptions;
        }
    }

    public void addDescription(Description description) {
        synchronized (descriptions){
            descriptions.add(description);
        }

    }

    public void removeDescription(long id) {
        synchronized (descriptions){
            Description description1 = new Description();
            for (Description description2 : descriptions){
                if (description2.getId() == id){
                    description1 = description2;
                }
            }
            descriptions.remove(description1);
        }
    }
}
