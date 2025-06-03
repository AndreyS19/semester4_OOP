package ru.ivt.mvc.foodDelivery.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
    private static int nextId = 1;
    private int id;
    private List<Integer> dishIds = new ArrayList<>(); // Изменено на список ID
    private String customerName;
    private String address;
    private LocalDateTime orderDate;
    private String status;
    private int userId;

    public Order(String customerName, String address, int userId) {
        this.id = nextId++;
        this.customerName = customerName;
        this.address = address;
        this.orderDate = LocalDateTime.now();
        this.status = "В обработке";
        this.userId = userId;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public List<Integer> getDishIds() { return dishIds; } // Новый геттер
    public void setDishIds(List<Integer> dishIds) { this.dishIds = dishIds; } // Новый сеттер
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getUserId() { return userId; }
    public LocalDateTime getOrderDate() { return orderDate; }
}