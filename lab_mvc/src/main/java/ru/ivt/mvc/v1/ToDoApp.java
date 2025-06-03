package ru.ivt.mvc.v1;// Основной класс приложения - такой код используют для тестов

public class ToDoApp {
    public static void main(String[] args) {
        TaskDAO instance = TaskDAO.getInstance();
        TaskView view = new TaskView();
        TaskController controller = new TaskController(instance);

        // Пример работы с приложением
        controller.addTask("Купить молоко");
        controller.addTask("Сделать домашнее задание");
        controller.viewTasks();

        controller.markTaskAsCompleted(1);
        controller.viewTasks();

        controller.deleteTask(2);
        controller.viewTasks();
    }
}