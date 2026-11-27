<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <p class="eyebrow">Открывайте и находите музыку</p>
        <h1>Галерея нот</h1>
        <p>Ищите произведения по названию или имени композитора.</p>
    </section>

    <form class="search-panel" action="score" method="get" role="search">
        <label for="score-query">Поиск по каталогу</label>
        <div class="search-row">
            <input id="score-query" name="q" type="search"
                   placeholder="Название произведения или композитор">
            <button type="submit">Найти ноты</button>
        </div>
    </form>

    <section class="catalog" aria-label="Ноты в каталоге">
        <div class="catalog-heading">
            <h2>Популярные ноты</h2>
            <span class="catalog-count">1 произведение</span>
        </div>

        <div class="score-grid">
            <c:forEach var="score" items="${list}">
                <article class="score-card">
                    <div class="score-card__topline">
                        <span class="score-card__icon" aria-hidden="true">♫</span>
                        <span class="score-badge">PDF</span>
                    </div>

                    <div class="score-card__content">
                        <h3>${score.title}</h3>
                        <p class="score-card__composer">${score.composerName}</p>

                        <dl class="score-details">
                            <div>
                                <dt>Сложность</dt>
                                <dd>${score.difficulity}</dd>
                            </div>
                        </dl>
                    </div>

                    <div class="score-card__footer">
                        <span>${score.date}</span>
                    </div>
                </article>
            </c:forEach>
<%--            <article class="score-card">--%>
<%--                <div class="score-card__topline">--%>
<%--                    <span class="score-card__icon" aria-hidden="true">♫</span>--%>
<%--                    <span class="score-badge">PDF</span>--%>
<%--                </div>--%>

<%--                <div class="score-card__content">--%>
<%--                    <p class="score-card__eyebrow">Классическая музыка</p>--%>
<%--                    <h3>Лунная соната</h3>--%>
<%--                    <p class="score-card__composer">Людвиг ван Бетховен</p>--%>

<%--                    <dl class="score-details">--%>
<%--                        <div>--%>
<%--                            <dt>Сложность</dt>--%>
<%--                            <dd>Средняя</dd>--%>
<%--                        </div>--%>
<%--                    </dl>--%>
<%--                </div>--%>

<%--                <div class="score-card__footer">--%>
<%--                    <span>Добавлено 24 ноября 2026</span>--%>
<%--                </div>--%>
<%--            </article>--%>
        </div>
    </section>
</main>

<jsp:include page="/jsp/fragments/footer.jsp" />
</body>
</html>
