package ru.ivt.mvc.foodDelivery.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.ivt.mvc.foodDelivery.model.Dish;
import ru.ivt.mvc.foodDelivery.DB.RestaurantManager;
import ru.ivt.mvc.foodDelivery.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/dishes")
public class DishServlet extends HttpServlet {
    private final RestaurantManager manager = RestaurantManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("admin");

        // Проверка прав администратора
        if (user == null || !"admin".equals(user.getRole())) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Обработка действий
        String action = req.getParameter("action");
        String idParam = req.getParameter("id");

        if (action != null && idParam != null) {
            try {
                int id = Integer.parseInt(idParam);

                if ("delete".equals(action)) {
                    manager.deleteDish(id);
                    resp.sendRedirect(req.getContextPath() + "/dishes");
                    return;
                } else if ("edit".equals(action)) {
                    // Получение блюда для редактирования
                    Dish dish = manager.getDishById(id);
                    if (dish != null) {
                        req.setAttribute("editDish", dish);
                    }
                    // Убрали редирект - остаемся на странице
                } else if ("toggle".equals(action)) {
                    manager.toggleDishAvailability(id);
                    resp.sendRedirect(req.getContextPath() + "/dishes");
                    return;
                }
            } catch (NumberFormatException e) {
                // Обработка ошибки
            }
        }

        req.setAttribute("dishes", manager.getAllDishes());
        req.getRequestDispatcher("/WEB-INF/view/dishes.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("admin");

        // Проверка прав администратора
        if (user == null || !"admin".equals(user.getRole())) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Определение типа операции
        String operation = req.getParameter("operation");
        String idParam = req.getParameter("id");
        boolean isUpdate = "update".equals(operation) && idParam != null && !idParam.isEmpty();

        // Валидация параметров
        String name = req.getParameter("name");
        String priceParam = req.getParameter("price");
        String category = req.getParameter("category");
        boolean available = "on".equals(req.getParameter("available")); // Новый параметр

        List<String> errors = new ArrayList<>();

        // ... существующая валидация ...

        // Обработка ошибок
        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.setAttribute("dishes", manager.getAllDishes());

            if (isUpdate) {
                try {
                    int id = Integer.parseInt(idParam);
                    Dish editDish = new Dish(id, name, Double.parseDouble(priceParam), category, available);
                    req.setAttribute("editDish", editDish);
                } catch (Exception e) {
                    // Обработка ошибки
                }
            }

            req.getRequestDispatcher("/WEB-INF/view/dishes.jsp").forward(req, resp);
            return;
        }

        // Создание/обновление блюда
        double price = Double.parseDouble(priceParam);
        Dish dish;

        if (isUpdate) {
            // Обновление существующего блюда
            int id = Integer.parseInt(idParam);
            dish = new Dish(id, name.trim(), price, category.trim(), available);
            manager.updateDish(dish);
        } else {
            // Добавление нового блюда
            dish = new Dish(name.trim(), price, category.trim());
            dish.setAvailable(available);
            manager.addDish(dish);
        }

        resp.sendRedirect(req.getContextPath() + "/dishes");
    }
}