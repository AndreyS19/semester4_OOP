<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, java.util.Map, ru.ivt.mvc.foodDelivery.model.Order, ru.ivt.mvc.foodDelivery.model.Dish" %>
<%@ page import="ru.ivt.mvc.foodDelivery.DB.RestaurantManager" %>
<%@ page import="ru.ivt.mvc.foodDelivery.model.User" %>
<%
    RestaurantManager manager = RestaurantManager.getInstance();
    User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Заказы</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet">
    <script>
        function confirmDelete(orderId) {
            return confirm('Вы уверены, что хотите удалить заказ #' + orderId + '?');
        }
    </script>
</head>
<body>
<div class="container mt-4">
    <!-- Навигационная панель с кнопкой выхода -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4 rounded">
        <div class="container-fluid">
            <a class="navbar-brand" href="<%= request.getContextPath() %>/orders">FoodDelivery</a>
            <div class="d-flex align-items-center">
                <% if (user != null) { %>
                <span class="navbar-text me-3">Пользователь: <%= user.getUsername() %></span>
                <% } %>
                <a class="btn btn-outline-light" href="<%= request.getContextPath() %>/logout">Выход</a>
            </div>
        </div>
    </nav>

    <div class="card">
        <div class="card-header">
            <h2 class="text-center">Управление заказами</h2>
        </div>

        <div class="card-body">
            <!-- Отображение ошибок -->
            <%
                List<String> errors = (List<String>) request.getAttribute("errors");
                if (errors != null && !errors.isEmpty()) {
            %>
            <div class="alert alert-danger mb-4">
                <h5>Ошибки:</h5>
                <ul>
                    <% for (String error : errors) { %>
                    <li><%= error %></li>
                    <% } %>
                </ul>
            </div>
            <% } %>

            <!-- Форма для создания/редактирования заказа -->
            <%
                Order editOrder = (Order) request.getAttribute("editOrder");
                List<Dish> editOrderDishes = (List<Dish>) request.getAttribute("editOrderDishes");
                boolean isEditMode = editOrder != null;
                boolean createMode = request.getAttribute("createMode") != null || (editOrder == null && !isEditMode);
            %>

            <% if (createMode || isEditMode) { %>
            <div class="mb-4 p-3 border rounded">
                <h4><%= isEditMode ? "Редактирование заказа #" + editOrder.getId() : "Создание нового заказа" %></h4>
                <form action="<%= request.getContextPath() %>/orders" method="post">
                    <input type="hidden" name="operation" value="<%= isEditMode ? "update" : "create" %>">
                    <% if (isEditMode) { %>
                    <input type="hidden" name="orderId" value="<%= editOrder.getId() %>">
                    <% } %>

                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="customerName" class="form-label">Имя клиента:</label>
                            <input type="text" class="form-control" id="customerName" name="customerName"
                                   value="<%= isEditMode ? editOrder.getCustomerName() : "" %>" required>
                        </div>
                        <div class="col-md-6">
                            <label for="address" class="form-label">Адрес:</label>
                            <input type="text" class="form-control" id="address" name="address"
                                   value="<%= isEditMode ? editOrder.getAddress() : "" %>" required>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Выберите блюда:</label>
                        <div class="row">
                            <%
                                List<Dish> allDishes = (List<Dish>) request.getAttribute("dishes");
                                for (Dish dish : allDishes) {
                                    boolean isSelected = false;
                                    if (isEditMode && editOrderDishes != null) {
                                        for (Dish editDish : editOrderDishes) {
                                            if (editDish.getId() == dish.getId()) {
                                                isSelected = true;
                                                break;
                                            }
                                        }
                                    }
                            %>
                            <div class="col-md-3 mb-2">
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox"
                                           name="dishIds" value="<%= dish.getId() %>"
                                           id="dish<%= dish.getId() %>"
                                        <%= isSelected ? "checked" : "" %>
                                        <%= dish.isAvailable() ? "" : "disabled" %>>
                                    <label class="form-check-label" for="dish<%= dish.getId() %>">
                                        <%= dish.getName() %> (<%= dish.getPrice() %> руб.)
                                        <%= dish.isAvailable() ? "" : "(недоступно)" %>
                                    </label>
                                </div>
                            </div>
                            <% } %>
                        </div>
                    </div>

                    <div class="d-flex gap-2">
                        <button type="submit" class="btn btn-primary">
                            <%= isEditMode ? "Обновить" : "Создать" %>
                        </button>
                        <a href="<%= request.getContextPath() %>/orders" class="btn btn-secondary">Отмена</a>
                    </div>
                </form>
            </div>
            <% } else { %>
            <div class="mb-4">
                <a href="<%= request.getContextPath() %>/orders?action=create" class="btn btn-primary">
                    Создать новый заказ
                </a>
            </div>
            <% } %>

            <!-- Таблица с заказами -->
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Клиент</th>
                        <th>Адрес</th>
                        <th>Блюда</th>
                        <th>Сумма</th>
                        <th>Действия</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<Order> orders = (List<Order>) request.getAttribute("orders");
                        Map<Integer, List<Dish>> orderDishesMap = (Map<Integer, List<Dish>>) request.getAttribute("orderDishesMap");
                        for (Order order : orders) {
                            List<Dish> orderDishes = orderDishesMap != null ? orderDishesMap.get(order.getId()) : List.of();
                            double total = 0.0;
                            if (orderDishes != null) {
                                for (Dish dish : orderDishes) {
                                    total += dish.getPrice();
                                }
                            }
                    %>
                    <tr>
                        <td><%= order.getId() %></td>
                        <td><%= order.getCustomerName() %></td>
                        <td><%= order.getAddress() %></td>
                        <td>
                            <ul class="list-unstyled">
                                <% if (orderDishes != null) {
                                    for (Dish dish : orderDishes) { %>
                                <li><%= dish.getName() %> (<%= dish.getPrice() %> руб.)</li>
                                <% }
                                } %>
                            </ul>
                        </td>
                        <td><%= total %> руб.</td>
                        <td>
                            <div class="d-flex gap-2">
                                <a href="<%= request.getContextPath() %>/orders?action=edit&id=<%= order.getId() %>"
                                   class="btn btn-warning btn-sm">
                                    Редактировать
                                </a>
                                <a href="<%= request.getContextPath() %>/orders?action=delete&id=<%= order.getId() %>"
                                   class="btn btn-danger btn-sm"
                                   onclick="return confirmDelete(<%= order.getId() %>)">
                                    Удалить
                                </a>
                            </div>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>