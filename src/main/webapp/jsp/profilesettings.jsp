<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Настройки профиля — NoteShare</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/profilesettings.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
</head>
<body>
<jsp:include page="/jsp/fragments/header.jsp" />
<main class="page-shell settings-page">
    <section class="page-heading">
        <p class="eyebrow">Личный кабинет</p><h1>Настройки профиля</h1>
        <p>Измените данные своего аккаунта.</p>
    </section>
    <form class="settings-form" action="profilesettings" method="post">
        <label for="settings-name">Имя пользователя</label>
        <input id="settings-name" name="username" type="text" autocomplete="username">
        <label for="settings-email">Электронная почта</label>
        <input id="settings-email" name="email" type="email" autocomplete="email">
        <button type="submit">Сохранить изменения</button>
        <a href="profile">Вернуться в профиль</a>
    </form>
</main>
<jsp:include page="/jsp/fragments/footer.jsp" />
</body>
</html>
