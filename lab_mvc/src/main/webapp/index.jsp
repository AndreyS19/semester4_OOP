<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Правильное перенаправление с учетом контекста приложения
    String contextPath = request.getContextPath();
    response.sendRedirect(contextPath + "/login");
%>