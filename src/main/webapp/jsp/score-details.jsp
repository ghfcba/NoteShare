<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><c:out value="${score.title}" default="Ноты"/> — NoteShare</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/score-details.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
</head>
<body>
<header class="site-header">
    <div class="header-inner">
        <a class="site-brand" href="${pageContext.request.contextPath}/main">
            <img class="site-logo" src="${pageContext.request.contextPath}/assets/images/logo.jpeg" alt="">
            <span>NoteShare</span>
        </a>
        <div class="menu-container">
            <button class="menu-toggle" type="button" aria-label="Открыть меню">
                <span class="menu-label">МЕНЮ</span>
            </button>
            <nav class="drop_menu" aria-label="Основная навигация">
                <ul>
                    <li><a href="${pageContext.request.contextPath}/main">Главная</a></li>
                    <li><a href="${pageContext.request.contextPath}/score">Галерея нот</a></li>
                    <li><a href="${pageContext.request.contextPath}/composer">Композиторы</a></li>
                    <li><a href="${pageContext.request.contextPath}/profile">Профиль</a></li>
                </ul>
            </nav>
        </div>
    </div>
</header>

<main class="page-shell score-detail-page">
    <a class="back-link" href="${pageContext.request.contextPath}/score">← Вернуться в галерею</a>

    <c:choose>
        <c:when test="${not empty score}">
            <article class="score-detail">
                <section class="score-intro">
                    <span class="score-icon" aria-hidden="true">♫</span>
                    <p class="eyebrow">Ноты произведения</p>
                    <h1><c:out value="${score.title}"/></h1>
                    <p class="composer-name">
                        <c:out value="${composer.fullName}" default="Композитор не указан"/>
                    </p>
                    <a class="download-button"
                       href="${pageContext.request.contextPath}/download?scoreId=${score.scoreId}">
                        Скачать PDF
                    </a>
                </section>

                <section class="score-info" aria-label="Информация о произведении">
                    <h2>О произведении</h2>
                    <dl class="info-list">
                        <div>
                            <dt>Жанр</dt>
                            <dd><c:out value="${genre.name}" default="Не указан"/></dd>
                        </div>
                        <div>
                            <dt>Сложность</dt>
                            <dd><c:out value="${score.difficulty}" default="Не указана"/></dd>
                        </div>
                        <div>
                            <dt>Добавлено</dt>
                            <dd><c:out value="${score.uploadedAt}" default="—"/></dd>
                        </div>
                        <div>
                            <dt>Формат файла</dt>
                            <dd>PDF</dd>
                        </div>
                    </dl>
                </section>

                <section class="pdf-section">
                    <div class="section-heading">
                        <h2>Просмотр нот</h2>
                        <a href="${pageContext.request.contextPath}/download?scoreId=${score.scoreId}">Открыть PDF отдельно</a>
                    </div>
                    <iframe class="pdf-viewer"
                            src="${pageContext.request.contextPath}/download?scoreId=${score.scoreId}#toolbar=0"
                            title="Ноты: ${score.title}">
                        Ваш браузер не поддерживает просмотр PDF.
                        <a href="${pageContext.request.contextPath}/download?scoreId=${score.scoreId}">Скачайте файл</a>.
                    </iframe>
                </section>
            </article>
        </c:when>
        <c:otherwise>
            <section class="not-found">
                <span class="score-icon" aria-hidden="true">♫</span>
                <h1>Ноты не найдены</h1>
                <p>Возможно, произведение было удалено или ссылка устарела.</p>
                <a class="download-button" href="${pageContext.request.contextPath}/score">Открыть галерею</a>
            </section>
        </c:otherwise>
    </c:choose>
</main>

<footer class="site-footer">
    <div class="footer-content">
        <span class="footer-decoration" aria-hidden="true">✦</span>
        <h2 class="footer-brand">NoteShare</h2>
        <p class="footer-description">Музыка, которой хочется делиться</p>
        <div class="footer-line" aria-hidden="true"></div>
        <p class="footer-copyright">© 2026 NoteShare</p>
    </div>
</footer>
</body>
</html>
