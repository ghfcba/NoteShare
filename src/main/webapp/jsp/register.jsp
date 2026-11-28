<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация — NoteShare</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/register.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
</head>
<body>
<jsp:include page="/jsp/fragments/header.jsp" />
<main class="auth-page">
    <section class="auth-card">
        <p class="eyebrow">Присоединяйтесь к NoteShare</p><h1>Создать аккаунт</h1>
        <p class="auth-description">Зарегистрируйтесь, чтобы делиться нотами.</p>
        <c:if test="${not empty error}">
            <p class="form-error"><c:out value="${error}"/></p>
        </c:if>
        <form class="auth-form" action="${pageContext.request.contextPath}/register" method="post">
            <label for="register-name">Имя пользователя</label>
            <input id="register-name" name="username" type="text" autocomplete="username"
                   maxlength="50" value="<c:out value='${username}'/>" required>
            <label for="register-email">Электронная почта</label>
            <input id="register-email" name="email" type="email" autocomplete="email"
                   maxlength="100" value="<c:out value='${email}'/>" required>
            <label for="register-password">Пароль</label>
            <input id="register-password" name="password" type="password" autocomplete="new-password"
                   minlength="8" maxlength="72" required>
            <label for="register-password-confirm">Повторите пароль</label>
            <input id="register-password-confirm" name="passwordConfirm" type="password" autocomplete="new-password"
                   minlength="8" maxlength="72" required>
            <button type="submit">Зарегистрироваться</button>
        </form>
        <p class="auth-switch">Уже есть аккаунт? <a href="${pageContext.request.contextPath}/login">Войти</a></p>
    </section>
</main>
<jsp:include page="/jsp/fragments/footer.jsp" />
</body>
</html>
