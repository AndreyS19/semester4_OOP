package ru.ivt.mvc.foodDelivery.model;

import java.io.Serializable;

public class Dish implements Serializable {
    private static int nextId = 1;
    private int id;
    private String name;
    private double price;
    private String category;
    private boolean available; // Новая поле - доступность блюда

    // Конструкторы
    public Dish(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.available = true; // По умолчанию доступно
    }

    public Dish(int id, String name, double price, String category, boolean available) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.available = available;
    }

    // Геттеры и сеттеры
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    public void setId(int id) {
        this.id = id;
    }
    // Геттеры
    public boolean getAvailable() {
        return available;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
}