<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Вход</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="auth-page">
<div class="auth-container">
    <h2 class="text-center mb-4">Вход в систему</h2>
    <!-- Исправленный action -->
    <form action="<%= request.getContextPath() %>/login" method="post">
        <div class="mb-3">
            <label class="form-label">Логин</label>
            <input type="text" name="username" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Пароль</label>
            <input type="password" name="password" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary w-100 mb-3">Войти</button>
        <!-- Исправленная ссылка -->
        <a href="<%= request.getContextPath() %>/register" class="btn btn-outline-secondary w-100">Регистрация</a>
        <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-danger mt-3"><%= request.getAttribute("error") %></div>
        <% } %>
        <% if (request.getAttribute("message") != null) { %>
        <div class="alert alert-success"><%= request.getAttribute("message") %></div>
        <% } %>
    </form>
</div>
</body>
</html>