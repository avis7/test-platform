package com.bionic.university.jsf;

/**
 * Created by rondo104 on 02.08.2015.
 */
public class Car {
    private String id;
    private  String color;
    private int year;
    private String brand;
    private int price;
    private boolean soldState;

    public Car(String id, String color, int year, String brand, int price, boolean soldState) {
        this.id = id;
        this.color = color;
        this.year = year;
        this.brand = brand;
        this.price = price;
        this.soldState = soldState;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSoldState() {
        return soldState;
    }

    public void setSoldState(boolean soldState) {
        this.soldState = soldState;
    }
}
