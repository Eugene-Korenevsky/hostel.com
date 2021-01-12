package com.spring.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class RoomForm {
    @NotNull
    @Min(value = 1,message = "min number value is 1")
    @JsonProperty("number")
    private int number;

    @NotNull
    @Min(value = 1,message = "min sits count is 1")
    @JsonProperty("sits")
    private int sits;

    @NotNull
    @Min(value = 0,message = "min price value is 0")
    @JsonProperty("price")
    private double price;

    @NotNull
    @Size(min = 1,message = "roomClass min size is 1")
    @JsonProperty("roomClass")
    private String roomClass;

    @JsonProperty("descriptions")
    private List<String> descriptions;

    @NotNull
    @JsonProperty("id")
    private long id;

    public RoomForm(int number, int sits, double price, String roomClass, List<String> descriptions, long id) {
        this.number = number;
        this.sits = sits;
        this.price = price;
        this.roomClass = roomClass;
        this.descriptions = descriptions;
        this.id = id;
    }

    public RoomForm() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSits() {
        return sits;
    }

    public void setSits(int sits) {
        this.sits = sits;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(ArrayList<String> descriptions) {
        this.descriptions = descriptions;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
