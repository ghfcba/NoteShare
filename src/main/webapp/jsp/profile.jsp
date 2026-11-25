<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Профиль — NoteShare</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/profile.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
</head>
<body>
<jsp:include page="/jsp/fragments/header.jsp" />
<main class="page-shell profile-page">
    <section class="page-heading">
        <p class="eyebrow">Ваше пространство</p><h1>Профиль</h1>
        <p>Информация аккаунта и ваши публикации.</p>
    </section>
    <section class="profile-panel">
        <div class="profile-avatar" aria-hidden="true">♫</div>
        <div class="profile-summary"><h2>Мой аккаунт</h2></div>
        <a class="text-link" href="profilesettings">Настройки профиля →</a>
    </section>
    <section class="profile-content">
        <h2>Мои ноты</h2>
        <p class="notice">У вас пока нет опубликованных нот.</p>
        <a href="login">Войти в другой аккаунт</a>
    </section>
</main>
<jsp:include page="/jsp/fragments/footer.jsp" />
</body>
</html>
