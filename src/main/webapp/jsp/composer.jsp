<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
<body class="composer-gallery-page">
<jsp:include page="/jsp/fragments/header.jsp" />

<main class="composer-page">
    <section class="page-heading">
        <h1>Композиторы</h1>
        <p>Ищите авторов и переходите к их произведениям.</p>
    </section>

    <c:url var="searchUrl" value="/composer" />
<%--    <form class="search-panel" action="${searchUrl}" method="get" role="search">--%>
<%--        <label for="composer-query">Имя композитора</label>--%>
<%--        <div class="search-row">--%>
<%--            <input id="composer-query" name="q" type="search" placeholder="Введите имя композитора">--%>
<%--            <button type="submit">Найти</button>--%>
<%--        </div>--%>
<%--    </form>--%>

    <section class="catalog" aria-label="Композиторы в каталоге">
        <c:choose>
            <c:when test="${empty composers}">
                <p class="composer-empty">Композиторы не найдены.</p>
            </c:when>
            <c:otherwise>
                <div class="composer-grid">
                    <c:forEach var="composer" items="${composers}">
                        <c:url var="composerUrl" value="/composer/page">
                            <c:param name="id" value="${composer.composerId}" />
                        </c:url>

                        <article class="composer-card">
                            <a class="composer-card__link" href="${composerUrl}">
                                <span class="composer-card__icon" aria-hidden="true">𝄞</span>
                                <div class="composer-card__content">
                                    <h2><c:out value="${composer.fullName}" /></h2>
                                    <c:if test="${not empty composer.country}">
                                        <p class="composer-card__country"><c:out value="${composer.country}" /></p>
                                    </c:if>
                                </div>
                                <span class="composer-card__action" aria-hidden="true">К произведениям →</span>
                            </a>
                        </article>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
    </section>
</main>

<jsp:include page="/jsp/fragments/footer.jsp" />
</body>
</html>
