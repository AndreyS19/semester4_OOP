package ru.ivt.mvc.v1;

import java.util.List;

public class TaskView {
    public void displayTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Список задач пуст.");
        } else {
            System.out.println("\n");
            for (Task task : tasks) {
                System.out.println(
                        "ID: " + task.getId() +
                        ", Описание: " + task.getDescription() +
                        ", Выполнено: " + (task.isCompleted() ? "Да" : "Нет"));
            }
            System.out.println("\n");
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
