package ru.ivt.mvc.v1;

// Это класс модели - еще такие классы назыаюют домены или доменный слой приложения - т.е. класс отвечающий за данные приложения
// Хранит нормализованные данные в виде полей и методов доступа к ним - только лишь
public class Task {
    // поля класса - нужные свойствв объекта
    private int id;
    private String description;
    private boolean isCompleted;

    // конструктор класса
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    // геттеры и сеттеры для полей класса
    public int getId() { return id; }
    public String getDescription() { return description; }
    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }
}
