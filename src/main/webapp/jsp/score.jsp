<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Галерея нот — NoteShare</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/score.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
</head>
<body>
<jsp:include page="/jsp/fragments/header.jsp" />
<main class="page-shell listing-page score-page">
    <section class="page-heading">
        <p class="eyebrow">Открывайте и находите музыку</p><h1>Галерея нот</h1>
        <p>Ищите произведения по названию или имени композитора.</p>
    </section>
    <form class="search-panel" action="score" method="get" role="search">
        <label for="score-query">Поиск по каталогу</label>
        <div class="search-row"><input id="score-query" name="q" type="search" placeholder="Название произведения или композитор"><button type="submit">Найти ноты</button></div>
    </form>
    <section class="empty-state" aria-live="polite">
        <span class="empty-icon" aria-hidden="true">♫</span>
        <h2>Каталог пока пуст</h2>
        <p>Когда ноты будут добавлены, они появятся в этом разделе.</p>
        <a href="login">Войти, чтобы поделиться нотами</a>
    </section>
</main>
<jsp:include page="/jsp/fragments/footer.jsp" />
</body>
</html>
