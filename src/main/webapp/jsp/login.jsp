<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Вход — NoteShare</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
</head>
<body>
<jsp:include page="/jsp/fragments/header.jsp" />
<main class="auth-page">
    <section class="auth-card">
        <p class="eyebrow">Рады видеть снова</p><h1>Вход в аккаунт</h1>
        <p class="auth-description">Войдите, чтобы сохранять и публиковать ноты.</p>
        <form class="auth-form" action="login" method="post">
            <label for="login-email">Электронная почта</label>
            <input id="login-email" name="email" type="email" autocomplete="email" required>
            <label for="login-password">Пароль</label>
            <input id="login-password" name="password" type="password" autocomplete="current-password" required>
            <button type="submit">Войти</button>
        </form>
        <p class="auth-switch">Ещё нет аккаунта? <a href="register">Зарегистрироваться</a></p>
    </section>
</main>
<jsp:include page="/jsp/fragments/footer.jsp" />
</body>
</html>
