<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, ru.ivt.mvc.foodDelivery.model.Dish" %>
<%@ page import="ru.ivt.mvc.foodDelivery.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>Меню</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet">
    <script>
        function confirmDelete(dishName) {
            return confirm('Вы уверены, что хотите удалить блюдо "' + dishName + '"?');
        }
    </script>
</head>
<body>
<%
    User admin = (User) session.getAttribute("admin");
%>
<div class="container mt-4">
    <!-- Навигационная панель с кнопкой выхода -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4 rounded">
        <div class="container-fluid">
            <a class="navbar-brand" href="<%= request.getContextPath() %>/dishes">FoodDelivery (Админ)</a>
            <div class="d-flex align-items-center">
                <% if (admin != null) { %>
                <span class="navbar-text me-3">Администратор: <%= admin.getUsername() %></span>
                <% } %>
                <a class="btn btn-outline-light" href="<%= request.getContextPath() %>/logout">Выход</a>
            </div>
        </div>
    </nav>

    <div class="card">
        <div class="card-header">
            <h2 class="text-center">Меню</h2>
        </div>

        <div class="card-body">
            <!-- Блок для отображения ошибок -->
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

            <!-- Форма для добавления/редактирования блюда -->
            <div class="mb-4 p-3 border rounded">
                <%
                    Dish editDish = (Dish) request.getAttribute("editDish");
                    boolean isEditMode = editDish != null;
                %>
                <h4><%= isEditMode ? "Редактировать блюдо" : "Добавить новое блюдо" %></h4>
                <form action="<%= request.getContextPath() %>/dishes" method="post">
                    <% if (isEditMode) { %>
                    <input type="hidden" name="operation" value="update">
                    <input type="hidden" name="id" value="<%= editDish.getId() %>">
                    <% } %>

                    <div class="row mb-3">
                        <div class="col-md-3">
                            <label for="name" class="form-label">Название:</label>
                            <input type="text" class="form-control" id="name" name="name"
                                   value="<%= isEditMode ? editDish.getName() : (request.getParameter("name") != null ? request.getParameter("name") : "") %>"
                                   required>
                        </div>
                        <div class="col-md-2">
                            <label for="price" class="form-label">Цена (руб.):</label>
                            <input type="number" step="0.01" min="0.01" class="form-control" id="price" name="price"
                                   value="<%= isEditMode ? editDish.getPrice() : (request.getParameter("price") != null ? request.getParameter("price") : "") %>"
                                   required>
                        </div>
                        <div class="col-md-2">
                            <label for="category" class="form-label">Категория:</label>
                            <input type="text" class="form-control" id="category" name="category"
                                   value="<%= isEditMode ? editDish.getCategory() : (request.getParameter("category") != null ? request.getParameter("category") : "") %>"
                                   required>
                        </div>
                        <div class="col-md-2">
                            <div class="form-check form-switch mt-4 pt-2">
                                <input class="form-check-input" type="checkbox" id="available" name="available"
                                    <%= (isEditMode && editDish.isAvailable()) || !isEditMode ? "checked" : "" %>>
                                <label class="form-check-label" for="available">Доступно</label>
                            </div>
                        </div>
                        <div class="col-md-3 d-flex align-items-end gap-2">
                            <button type="submit" class="btn btn-primary flex-grow-1">
                                <%= isEditMode ? "Обновить" : "Добавить" %>
                            </button>
                            <% if (isEditMode) { %>
                            <a href="<%= request.getContextPath() %>/dishes" class="btn btn-secondary">Отмена</a>
                            <% } %>
                        </div>
                    </div>
                </form>
            </div>

            <!-- Таблица с блюдами -->
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Название</th>
                        <th>Цена</th>
                        <th>Категория</th>
                        <th>Доступно</th>
                        <th>Действия</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<Dish> dishes = (List<Dish>) request.getAttribute("dishes");
                        for (Dish dish : dishes) {
                    %>
                    <tr>
                        <td><%= dish.getId() %></td>
                        <td><%= dish.getName() %></td>
                        <td><%= dish.getPrice() %> руб.</td>
                        <td><%= dish.getCategory() %></td>
                        <td>
                            <div class="form-check form-switch">
                                <input class="form-check-input" type="checkbox"
                                    <%= dish.isAvailable() ? "checked" : "" %>
                                       onclick="window.location.href='<%= request.getContextPath() %>/dishes?action=toggle&id=<%= dish.getId() %>'">
                            </div>
                        </td>
                        <td>
                            <div class="d-flex gap-2">
                                <a href="<%= request.getContextPath() %>/dishes?action=edit&id=<%= dish.getId() %>"
                                   class="btn btn-warning btn-sm">
                                    Редактировать
                                </a>
                                <a href="<%= request.getContextPath() %>/dishes?action=delete&id=<%= dish.getId() %>"
                                   class="btn btn-danger btn-sm"
                                   onclick="return confirmDelete('<%= dish.getName() %>')">
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