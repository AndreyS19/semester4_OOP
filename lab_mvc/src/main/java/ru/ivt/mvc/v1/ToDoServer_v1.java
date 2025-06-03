package ru.ivt.mvc.v1;// Основной класс приложения - это рабочий подход (ненастоящий)

import java.util.Scanner;

public class ToDoServer_v1 {
    private static TaskDAO instance = TaskDAO.getInstance();
    private static TaskController controller = new TaskController(instance);

    public static void main(String[] args) {

        Scanner myObj = new Scanner(System.in);
        while (true){
            String input = myObj.nextLine();
            String[] comParam = input.split(" ", 2);
            switch (comParam[0]) {
                case "add":
                    controller.addTask(comParam[1]);
                    break;
                case "view":
                    controller.viewTasks();
                    break;
                case "mark":
                    controller.markTaskAsCompleted(Integer.valueOf(comParam[1]));
                    break;
                default:
                    break;
            }
        }
    }
}
