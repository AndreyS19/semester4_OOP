package ru.ivt.mvc.v2;

// Это класс модели - еще такие классы назыаюют домены или доменный слой приложения - т.е. класс отвечающий за данные приложения
// Хранит нормализованные данные в виде полей и методов доступа к ним - только лишь
public class Task {
    // поля класса - нужные свойствв объекта
    private int id;
    private User user;
    private String description;
    private boolean isCompleted;

    // конструктор класса
    public Task(String description, User user) {
        this.user = user;
        this.description = description;
        this.isCompleted = false;
    }

    // геттеры и сеттеры для полей класса
    public int getId() { return id; }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = description;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }
}
