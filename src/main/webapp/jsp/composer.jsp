<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<main class="page-shell listing-page composer-page">
    <section class="page-heading">
        <p class="eyebrow">Музыкальные авторы</p>
        <h1>Композиторы</h1>
        <p>Ищите авторов и переходите к их произведениям.</p>
    </section>

    <form class="search-panel" action="composer" method="get" role="search">
        <label for="composer-query">Имя композитора</label>
        <div class="search-row">
            <input id="composer-query" name="q" type="search" placeholder="Введите имя композитора">
            <button type="submit">Найти</button>
        </div>
    </form>

    <section class="catalog" aria-label="Композиторы в каталоге">
        <div class="catalog-heading">
            <h2>Композиторы</h2>
            <span class="catalog-count">1 композитор</span>
        </div>

        <div class="composer-grid">
            <c:forEach var="composer" items="${composers}">
            <article class="composer-card">
                <div class="composer-card__topline">
                    <span class="composer-card__icon" aria-hidden="true">𝄞</span>
                </div>

                <div class="composer-card__content">
                    <p class="composer-card__eyebrow">Композитор</p>
                    <h3>${composer.fullName}</h3>
                    <dl class="composer-details">
                        <div>
                            <dt>Страна</dt>
                            <dd>${composer.country}</dd>
                        </div>

                    </dl>
                </div>
            </article>


            </c:forEach>
        </div>
    </section>
</main>

<jsp:include page="/jsp/fragments/footer.jsp" />
</body>
</html>
