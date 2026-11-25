<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Композиторы — NoteShare</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/composer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
</head>
<body>
<jsp:include page="/jsp/fragments/header.jsp" />
<main class="page-shell listing-page">
    <section class="page-heading">
        <p class="eyebrow">Музыкальные авторы</p><h1>Композиторы</h1>
        <p>Ищите авторов и переходите к их произведениям.</p>
    </section>
    <form class="search-panel" action="composer" method="get" role="search">
        <label for="composer-query">Имя композитора</label>
        <div class="search-row"><input id="composer-query" name="q" type="search" placeholder="Введите имя композитора"><button type="submit">Найти</button></div>
    </form>
    <section class="empty-state" aria-live="polite">
        <span class="empty-icon" aria-hidden="true">𝄞</span>
        <h2>Композиторов пока нет</h2>
        <p>Добавленные композиторы появятся здесь.</p>
    </section>
</main>
<jsp:include page="/jsp/fragments/footer.jsp" />
</body>
</html>
