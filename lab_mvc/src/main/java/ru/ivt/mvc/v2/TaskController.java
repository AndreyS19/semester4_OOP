// это класс преобразован в сервлет
package ru.ivt.mvc.v2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

// Этот класс отвечает за бизнес-логику приложения - теперь это сервлет джавы - его сервер приложений может запускать благодаря аннотациям
@WebServlet("/task")
public class TaskController extends HttpServlet {
    private final DAOInMemoryTask dataBase;

    public TaskController() {
        super();
        this.dataBase = DAOInMemoryTask.getInstance();
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String description = request.getParameter("description");
        String user_name = request.getParameter("user_name");
        User user = new User(user_name);
        Task task = new Task(description, user);
        int id = dataBase.addTask(task);
        request.setAttribute("description", description);
        request.setAttribute("name", user_name);
        getServletContext().getRequestDispatcher("/view/task.jsp").forward(request, response);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Task> tasks = dataBase.getAllTasks();

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        dataBase.markTaskAsCompleted(Integer.valueOf(id));
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        dataBase.deleteTask(Integer.valueOf(id));
    }
}
