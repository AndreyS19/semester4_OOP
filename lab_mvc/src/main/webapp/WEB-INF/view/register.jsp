<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Регистрация</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="auth-page">
<div class="auth-container">
    <h2 class="text-center mb-4">Регистрация</h2>
    <!-- Исправленный action -->
    <form action="<%= request.getContextPath() %>/register" method="post">
        <div class="mb-3">
            <label class="form-label">Логин</label>
            <input type="text" name="username" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Пароль</label>
            <input type="password" name="password" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-success w-100 mb-3">Зарегистрироваться</button>
        <!-- Исправленная ссылка -->
        <a href="<%= request.getContextPath() %>/login" class="btn btn-outline-secondary w-100">Вход</a>
        <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-danger mt-3"><%= request.getAttribute("error") %></div>
        <% } %>
    </form>
</div>
</body>
</html>