package com.spring.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class RoomForm {
    @JsonProperty("number")
    private int number;
    @JsonProperty("sits")
    private int sits;
    @JsonProperty("price")
    private double price;
    @JsonProperty("roomClass")
    private String roomClass;
    @JsonProperty("descriptions")
    private ArrayList<String> descriptions;
    @JsonProperty("id")
    private long id;

    public RoomForm(int number, int sits, double price, String roomClass, ArrayList<String> descriptions, long id) {
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

    public ArrayList<String> getDescriptions() {
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
}
