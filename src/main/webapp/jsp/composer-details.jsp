<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><c:out value="${composer.fullName}" default="Композитор"/> — NoteShare</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/composer-details.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
</head>
<body>
<jsp:include page="/jsp/fragments/header.jsp" />


<main class="page-shell composer-page">
    <a class="back-link" href="${pageContext.request.contextPath}/composers">← Все композиторы</a>

    <c:choose>
        <c:when test="${not empty composer}">
            <section class="composer-profile">
                <div class="composer-profile__icon" aria-hidden="true">𝄞</div>
                <div class="composer-profile__info">
                    <p class="eyebrow">Композитор</p>
                    <h1><c:out value="${composer.fullName}"/></h1>
                    <p class="composer-country">
                        <span aria-hidden="true">⌖</span>
                        <c:out value="${composer.country}" default="Страна не указана"/>
                    </p>
                </div>
            </section>

            <section class="scores-section">
                <div class="section-heading">
                    <div>
                        <p class="eyebrow">Произведения автора</p>
                        <h2>Ноты</h2>
                    </div>
                </div>

                <c:choose>
                    <c:when test="${not empty scoreList}">
                        <div class="scores-list">
                            <c:forEach var="score" items="${scoreList}">

                                <a class="score-item"
                                   href="${pageContext.request.contextPath}/score/details?id=${score.scoreId}">
                                    <span class="score-item__icon" aria-hidden="true">♫</span>
                                    <span class="score-item__main">
                                        <span class="score-item__title"><c:out value="${score.title}"/></span>
                                        <span class="score-item__meta">
                                            <span>PDF</span>
                                            <c:if test="${not empty score.difficulty}">
                                                <span><c:out value="${score.difficulty}"/></span>
                                            </c:if>
                                        </span>
                                    </span>
                                    <span class="score-item__arrow" aria-hidden="true">→</span>
                                </a>
                            </c:forEach>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="empty-state">
                            <span class="empty-state__icon" aria-hidden="true">♫</span>
                            <h3>Нот этого композитора пока нет</h3>
                            <p>Когда произведения появятся в каталоге, они будут отображаться здесь.</p>
                        </div>
                    </c:otherwise>
                </c:choose>
            </section>
        </c:when>
        <c:otherwise>
            <section class="empty-state not-found">
                <span class="empty-state__icon" aria-hidden="true">𝄞</span>
                <h1>Композитор не найден</h1>
                <p>Возможно, ссылка устарела или запись была удалена.</p>
                <a class="return-button" href="${pageContext.request.contextPath}/composer">Вернуться к композиторам</a>
            </section>
        </c:otherwise>
    </c:choose>
</main>

<footer class="site-footer">
    <div class="footer-wave" aria-hidden="true">
        <svg viewBox="0 0 1440 120" preserveAspectRatio="none">
            <g fill="none" stroke="currentColor" stroke-width="2.5">
                <path d="M0 12 C240 74 420 -31 720 29 S1160 79 1440 6" />
                <path d="M0 21 C240 83 420 -22 720 38 S1160 88 1440 15" />
                <path d="M0 30 C240 92 420 -13 720 47 S1160 97 1440 24" />
                <path d="M0 39 C240 101 420 -4 720 56 S1160 106 1440 33" />
                <path d="M0 48 C240 110 420 5 720 65 S1160 115 1440 42" />
            </g>
        </svg>
    </div>
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
