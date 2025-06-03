//package ru.ivt.mvc.v2;
//import ru.ivt.mvc.v2.TaskController;
//import ru.ivt.mvc.v2.DAOInMemoryTask;
//
//import java.util.Scanner;
//public class ToDoServer {
//    private static DAOInMemoryTask instance = DAOInMemoryTask.getInstance();
//    private static ru.ivt.mvc.v2.TaskController controller = new TaskController();
//    public static void main(String[] args) {
//        Scanner myObj = new Scanner(System.in);
//        while (true){
//            String input = myObj.nextLine();
//            String[] comParam = input.split(" ", 2);
//            switch (comParam[0]) {
//                case "add":
//                    controller.addTask(comParam[1]);
//                    break;
//                case "view":
//                    controller.viewTasks();
//                    break;
//                case "mark":
//                    controller.markTaskAsCompleted(Integer.valueOf(comParam[1]));
//                    break;
//                default:
//                    break;
//            }
//        }
//}
