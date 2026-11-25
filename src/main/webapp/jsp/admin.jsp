<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Управление NoteShare</title>
    <link rel="stylesheet" href="../../webapp/assets/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
</head>
<body>
<jsp:include page="/jsp/fragments/header.jsp" />
<main class="page-shell admin-page">
    <section class="page-heading">
        <p class="eyebrow">NoteShare</p><h1>Панель администратора</h1>
        <p>Управление разделами сайта и содержимым каталога.</p>
    </section>
    <section class="admin-sections" aria-label="Разделы управления">
        <a class="admin-card" href="score"><span class="card-mark">♫</span><h2>Каталог нот</h2><p>Просмотр и управление нотами.</p><span class="text-link">Открыть каталог →</span></a>
        <a class="admin-card" href="composer"><span class="card-mark">𝄞</span><h2>Композиторы</h2><p>Просмотр и управление списком композиторов.</p><span class="text-link">Открыть раздел →</span></a>
        <a class="admin-card" href="main"><span class="card-mark">⌂</span><h2>Главная страница</h2><p>Вернуться на главную NoteShare.</p><span class="text-link">На главную →</span></a>
    </section>
    <p class="notice">Пока в панели нет записей для управления.</p>
</main>
<jsp:include page="/jsp/fragments/footer.jsp" />
</body>
</html>
