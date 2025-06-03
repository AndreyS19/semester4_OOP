package ru.ivt.mvc.foodDelivery.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.ivt.mvc.foodDelivery.DB.RestaurantManager;
import ru.ivt.mvc.foodDelivery.model.User;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final RestaurantManager manager = RestaurantManager.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRegister(req, resp);
    }

    private void processRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Используем правильный метод проверки существования пользователя
        if (manager.userExists(username)) {
            // Устанавливаем атрибут ошибки
            req.setAttribute("error", "Пользователь уже существует");
            // Возвращаем на страницу регистрации с сообщением об ошибке
            req.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(req, resp);
        } else {
            manager.addUser(new User(username, password, "user"));
            // Перенаправляем на страницу входа с параметром успешной регистрации
            resp.sendRedirect(req.getContextPath() + "/login?registered=true");
        }
    }
}
