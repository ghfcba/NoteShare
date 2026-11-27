<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
<body class="score-gallery-page">
<jsp:include page="/jsp/fragments/header.jsp" />

<main class="score-page">
    <section class="page-heading">
        <p class="eyebrow">Открывайте и находите музыку</p>
        <h1>Галерея нот</h1>
        <p>Ищите произведения по названию или имени композитора.</p>
    </section>

    <c:url var="searchUrl" value="/score" />
    <form class="search-panel" action="${searchUrl}" method="get" role="search">
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
        </div>

        <c:if test="${empty list}">
            <p class="score-empty">В каталоге пока нет нот для показа.</p>
        </c:if>

        <div class="score-grid">
            <c:forEach var="score" items="${list}">
                <c:url var="itemUrl" value="/score/page">
                    <c:param name="id" value="${score.scoreId}"/>
                </c:url>

                <article class="score-card">
                    <a class="score-card__link" href="${itemUrl}">
                        <div class="score-card__topline">
                            <span class="score-card__icon" aria-hidden="true">♫</span>
                            <span class="score-badge">PDF</span>
                        </div>

                        <div class="score-card__content">
                            <h3><c:out value="${score.title}" /></h3>
                            <p class="score-card__composer"><c:out value="${score.composerName}" /></p>

                            <dl class="score-details">
                                <div>
                                    <dt>Сложность</dt>
                                    <dd><c:out value="${score.difficulity}" /></dd>
                                </div>
                            </dl>
                        </div>

                        <div class="score-card__footer">
                            <span><c:out value="${score.date}" /></span>
                            <span class="score-card__action" aria-hidden="true">Открыть ноты →</span>
                        </div>
                    </a>
                </article>
            </c:forEach>
        </div>
    </section>
</main>

<jsp:include page="/jsp/fragments/footer.jsp" />
</body>
</html>
