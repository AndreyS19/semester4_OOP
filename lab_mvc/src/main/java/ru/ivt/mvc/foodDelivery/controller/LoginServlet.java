package ru.ivt.mvc.foodDelivery.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.ivt.mvc.foodDelivery.DB.RestaurantManager;
import ru.ivt.mvc.foodDelivery.model.User;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final RestaurantManager manager = RestaurantManager.getInstance();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Проверка параметра успешной регистрации
        String registered = request.getParameter("registered");
        if ("true".equals(registered)) {
            System.out.println("Prov");
            request.setAttribute("message", "Регистрация прошла успешно! Войдите в систему.");
        }

        // Отображение страницы входа
        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        processLogin(req, resp);
    }
    private void processLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = manager.findUser(username, password);

        if (user != null) {
            HttpSession session = req.getSession();
            if(user.getRole().equals("admin"))
            {
                session.setAttribute("admin", user);
                resp.sendRedirect(req.getContextPath() + "/dishes");
            }
            else
            {
                session.setAttribute("user", user);
                resp.sendRedirect(req.getContextPath() + "/orders");
            }
        }
        else
        {
            // Сохраняем введенное имя для повторного отображения
            req.setAttribute("enteredUsername", username);
            req.setAttribute("error", "Неверный логин или пароль");

            // Перенаправляем прямо на JSP, а не на сервлет
            req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
        }

    }
}
