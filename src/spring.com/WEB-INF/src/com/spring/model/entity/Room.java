package com.spring.model.entity;

import javax.persistence.*;
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
    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

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

    public void removeDescription(long id) {
        Description description1 = new Description();
        for (Description description2 : descriptions) {
            if (description2.getId() == id) {
                description1 = description2;
            }
        }
    }
}
