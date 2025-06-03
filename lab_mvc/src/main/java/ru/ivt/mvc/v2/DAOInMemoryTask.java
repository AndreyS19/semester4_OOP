package ru.ivt.mvc.v2;

import java.util.ArrayList;
import java.util.List;

// Этот класс имплементирует работу с данными согласно интерфейсу
// Отвечает за метод работы с данными
public class DAOInMemoryTask implements DAO{
    // Хранилеще данных
    private List<Task> tasks;


    //// singleton - получаем только один экземпляр у класса БД
    // экземпляр класса БД
    private static DAOInMemoryTask instance;
    private DAOInMemoryTask() {
        tasks = new ArrayList<Task>();
    }
    public static DAOInMemoryTask getInstance() {
        if (instance == null) {
            instance = new DAOInMemoryTask();
        }
        return instance;
    }


    @Override
    public int addTask(Task task) {
        task.setId(IdGenerator.getNextId());
        tasks.add(task);
        return task.getId();
    }
    @Override
    public boolean deleteTask(int id) {
        return tasks.removeIf(task -> task.getId() == id);
    }

    @Override
    public Task markTaskAsCompleted(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setCompleted(true);
                return task;
            }
        }
        return null;
    }

    @Override
    public Task getTasks(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    @Override
    public List<Task> getAllTasks() {
        return tasks;
    }
}
