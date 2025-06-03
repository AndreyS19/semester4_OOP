package ru.ivt.mvc.v2;

import java.util.List;

//CRUD операции с данными - можно создадать разные классы которые реализуют работу с данными но они должны иметь методы этого интерфейса
public interface DAO {
    // CREATE
    public int addTask(Task task);
    // DELETE
    public boolean deleteTask(int id);
    // UPDATE
    public Task markTaskAsCompleted(int id);
    // READ
    public Task getTasks(int id);
    public List<Task> getAllTasks();
}
