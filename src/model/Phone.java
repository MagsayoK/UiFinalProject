/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;


/**
 *
 * @author USER
 */
public class Phone {
    private int phoneId;
    private String brand;
    private String model;
    private LocalDate releaseDate;
    private double price;

    public Phone(int phoneId, String brand, String model,
                 LocalDate releaseDate, double price) {
        this.phoneId = phoneId;
        this.brand = brand;
        this.model = model;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public int getPhoneId() {
        return phoneId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public double getPrice() {
        return price;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
