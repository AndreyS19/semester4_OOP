package ru.ivt.mvc.foodDelivery.model;

import java.io.Serializable;

public class User implements Serializable {
    private static int nextId = 1;
    private int id;
    private String username;
    private String password;
    private String role; // "admin" или "user"

    public User(String username, String password, String role) {
        this.id = nextId++;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Геттеры
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public int getId() { return id; }
}