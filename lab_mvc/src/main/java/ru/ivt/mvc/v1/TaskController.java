package ru.ivt.mvc.v1;

import jakarta.servlet.http.HttpServlet;

import java.util.List;

// Этот класс отвечает за бизнес-логику приложения
public class TaskController {
    private TaskDAO dataBase;
    private TaskView view;

    public TaskController(TaskDAO dataBase) {
        this.dataBase = dataBase;
        this.view = new TaskView();
    }

    public void addTask(String description) {
        Task task = new Task(description);
        int id = dataBase.addTask(task);
        view.showMessage("Задача добавлена. ID:"+String.valueOf(id));
    }

    public void viewTasks() {
        List<Task> tasks = dataBase.getAllTasks();
        view.displayTasks(tasks);
    }

    public void markTaskAsCompleted(int id) {
        dataBase.markTaskAsCompleted(id);
        view.showMessage("Задача отмечена как выполненная. ID:"+String.valueOf(id));
    }

    public void deleteTask(int id) {
        dataBase.deleteTask(id);
        view.showMessage("Задача удалена. ID:"+String.valueOf(id));
    }

}
