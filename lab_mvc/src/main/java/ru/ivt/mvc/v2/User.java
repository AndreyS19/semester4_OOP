package ru.ivt.mvc.v2;

import java.util.ArrayList;
import java.util.List;

// Это класс модели - еще такие классы назыаюют домены или доменный слой приложения - т.е. класс отвечающий за данные приложения
// Хранит нормализованные данные в виде полей и методов доступа к ним - только лишь
public class User {
    private String name;
    ArrayList<Task> tasks = new ArrayList<>();


    public User(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Task> getTasks() {
        return tasks;
    }
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}
