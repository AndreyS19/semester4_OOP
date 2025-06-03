package ru.ivt.mvc.v1;

import java.util.ArrayList;
import java.util.List;

//CRUD операции с данными
// Этот класс релизует рабуту с данными
// Отвечает за метод работы с данными
public class TaskDAO {
    // Хранилеще данных
    private List<Task> tasks;


    //// singleton - получаем только один экземпляр у класса БД
    // экземпляр класса БД
    private static TaskDAO instance;
    // поле хранит ID для каждой новой Task
    private static int nextId;

    private TaskDAO() {
        tasks = new ArrayList<Task>();
        nextId = 1;
    }

    public static TaskDAO getInstance() {
        if (instance == null) {
            instance = new TaskDAO();
        }
        return instance;
    }

    public static int getNextId() {
        return nextId++;
    }





    public int addTask(Task task) {
        tasks.add(task);
        return task.getId();
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task markTaskAsCompleted(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setCompleted(true);
                return task;
            }
        }
        return null;
    }

    public boolean deleteTask(int id) {
        return tasks.removeIf(task -> task.getId() == id);
    }
}
