<%@ page contentType="text/html; charset=UTF-8" %>
<header class="site-header">
    <div class="menu-container">
        <button class="menu-toggle" type="button" aria-label="Меню сайта">
            <span class="menu-label">МЕНЮ</span>
        </button>

        <nav class="drop_menu" aria-label="Основная навигация">
            <ul>
                <li><a href="${pageContext.request.contextPath}/main">Главная</a></li>
                <li><a href="${pageContext.request.contextPath}/score">Галерея нот</a></li>
                <li><a href="${pageContext.request.contextPath}/composers">Композиторы</a></li>
                <li><a href="${pageContext.request.contextPath}/login">Войти</a></li>
            </ul>
        </nav>
    </div>
</header>
