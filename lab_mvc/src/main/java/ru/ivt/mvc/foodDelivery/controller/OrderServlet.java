package ru.ivt.mvc.foodDelivery.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.ivt.mvc.foodDelivery.DB.RestaurantManager;
import ru.ivt.mvc.foodDelivery.model.Dish;
import ru.ivt.mvc.foodDelivery.model.Order;
import ru.ivt.mvc.foodDelivery.model.User;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Collectors;

@WebServlet("/orders")
public class OrderServlet extends HttpServlet {
    private final RestaurantManager manager = RestaurantManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String action = req.getParameter("action");
        String idParam = req.getParameter("id");

        List<String> errors = new ArrayList<>();

        // Получаем заказы только текущего пользователя
        List<Order> userOrders = manager.getOrdersForUser(user.getId());
        req.setAttribute("orders", userOrders);
        req.setAttribute("dishes", manager.getAllDishes());

        if (action != null && idParam != null) {
            try {
                int orderId = Integer.parseInt(idParam);
                Optional<Order> orderOpt = manager.getOrderById(orderId);

                if (orderOpt.isPresent()) {
                    Order order = orderOpt.get();

                    // Проверка принадлежности заказа пользователю
                    if (order.getUserId() == user.getId()) {
                        if ("edit".equals(action)) {
                            req.setAttribute("editOrder", order);
                        } else if ("delete".equals(action)) {
                            manager.deleteOrder(orderId);
                            // Обновляем список заказов после удаления
                            req.setAttribute("orders", manager.getOrdersForUser(user.getId()));
                        }
                    } else {
                        errors.add("У вас нет прав доступа к этому заказу");
                    }
                } else {
                    errors.add("Заказ не найден");
                }
            } catch (NumberFormatException e) {
                errors.add("Неверный идентификатор заказа");
            }
        }

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
        }

        req.getRequestDispatcher("/WEB-INF/view/orders.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String operation = req.getParameter("operation");
        String orderIdParam = req.getParameter("orderId");
        boolean isUpdate = "update".equals(operation) && orderIdParam != null && !orderIdParam.isEmpty();

        // Общие параметры
        String customerName = req.getParameter("customerName");
        String address = req.getParameter("address");
        String[] dishIds = req.getParameterValues("dishIds");
        String status = req.getParameter("status");

        List<String> errors = new ArrayList<>();

        // Проверка прав для операции обновления
        if (isUpdate) {
            // Обновление существующего заказа
            int orderId = Integer.parseInt(orderIdParam);
            Optional<Order> orderOpt = manager.getOrderById(orderId);

            if (orderOpt.isPresent()) {
                Order order = orderOpt.get();
                order.setCustomerName(customerName.trim());
                order.setAddress(address.trim());

                // Обновляем список ID блюд
                List<Integer> dishIdsList = Arrays.stream(dishIds)
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                order.setDishIds(dishIdsList);

                if (status != null) {
                    order.setStatus(status);
                }

                manager.updateOrder(order);
            }
        }if (isUpdate) {
            int orderId = Integer.parseInt(orderIdParam);
            Optional<Order> orderOpt = manager.getOrderById(orderId);

            if (orderOpt.isPresent()) {
                Order order = orderOpt.get();
                order.setCustomerName(customerName.trim());
                order.setAddress(address.trim());

                List<Integer> dishIdsList = Arrays.stream(dishIds)
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                order.setDishIds(dishIdsList);

                // УДАЛЕНО: обновление статуса
                manager.updateOrder(order);
            }
        } else {
            // Создание нового заказа
            Order order = new Order(customerName.trim(), address.trim(), user.getId());

            // Получаем список блюд по ID
            List<Dish> selectedDishes = Arrays.stream(dishIds)
                    .map(Integer::parseInt)
                    .map(manager::getDishById)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            manager.createOrder(order, selectedDishes);
        }

        resp.sendRedirect(req.getContextPath() + "/orders");
    }
}