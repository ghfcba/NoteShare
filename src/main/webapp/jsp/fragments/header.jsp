<%@ page contentType="text/html; charset=UTF-8" %>
<%
    Object signedInUser = session.getAttribute("user");
    if (signedInUser == null) signedInUser = session.getAttribute("currentUser");
    if (signedInUser == null) signedInUser = session.getAttribute("loggedInUser");

    boolean isSignedIn = signedInUser != null
            || session.getAttribute("userId") != null
            || session.getAttribute("username") != null
            || session.getAttribute("email") != null;

    boolean isAdmin = Boolean.TRUE.equals(session.getAttribute("isAdmin"))
            || "admin".equalsIgnoreCase(String.valueOf(session.getAttribute("role")));
%>
<header class="site-header">
    <div class="menu-container">
        <button class="menu-toggle" type="button" aria-label="Меню сайта">
            <span class="menu-label">МЕНЮ</span>
        </button>

        <nav class="drop_menu" aria-label="Основная навигация">
            <ul>
                <li><a class="nav-link" href="${pageContext.request.contextPath}/main">Главная</a></li>
                <li><a class="nav-link" href="${pageContext.request.contextPath}/score">Галерея нот</a></li>
                <li><a class="nav-link" href="${pageContext.request.contextPath}/composers">Композиторы</a></li>
                <li><a class="nav-link" href="${pageContext.request.contextPath}/upload">Загрузить ноты</a></li>
                <% if (isSignedIn) { %>
                    <li><a class="nav-link account-link" href="${pageContext.request.contextPath}/profile">Профиль</a></li>
                <% } else { %>
                    <li><a class="nav-link" href="${pageContext.request.contextPath}/login">Войти</a></li>
                    <li><a class="nav-link account-link" href="${pageContext.request.contextPath}/register">Регистрация</a></li>
                <% } %>
                <% if (isAdmin) { %>
                    <li><a class="nav-link" href="${pageContext.request.contextPath}/admin">Администрирование</a></li>
                <% } %>
            </ul>
        </nav>
    </div>
</header>
