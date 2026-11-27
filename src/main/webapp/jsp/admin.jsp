<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Модерация нот — NoteShare</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
</head>
<body>
<jsp:include page="/jsp/fragments/header.jsp" />


<main class="page-shell admin-page">
    <section class="admin-heading">
        <div>
            <p class="eyebrow">NoteShare · управление контентом</p>
            <h1>Модерация нот</h1>
            <p>Проверьте загруженные произведения и установите их статус.</p>
        </div>
        <span class="admin-mark" aria-hidden="true">♫</span>
    </section>

    <c:if test="${not empty successMessage}">
        <div class="alert alert-success" role="status"><c:out value="${successMessage}"/></div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-error" role="alert"><c:out value="${errorMessage}"/></div>
    </c:if>

    <section class="moderation-panel">
        <div class="panel-toolbar">
            <div>
                <h2>Загруженные произведения</h2>
                <p>Статус <strong>pending</strong> означает, что ноты ожидают проверки.</p>
            </div>
            <form class="filter-form" action="${pageContext.request.contextPath}/admin" method="get">
                <label for="moderation-filter">Показать</label>
                <select id="moderation-filter" name="moderationStatus">
                    <option value="">Все статусы</option>
                    <option value="pending" <c:if test="${param.moderationStatus eq 'pending'}">selected</c:if>>На проверке</option>
                    <option value="approved" <c:if test="${param.moderationStatus eq 'approved'}">selected</c:if>>Одобрено</option>
                    <option value="rejected" <c:if test="${param.moderationStatus eq 'rejected'}">selected</c:if>>Отклонено</option>
                </select>
                <button type="submit" class="filter-button">Применить</button>
            </form>
        </div>

        <c:choose>
            <c:when test="${not empty scoreList}">
                <div class="admin-table-wrap">
                    <table class="admin-table">
                        <thead>
                        <tr>
                            <th>Произведение</th>
                            <th>Загрузил</th>
                            <th>Композитор</th>
                            <th>Сложность</th>
                            <th>Загружено</th>
                            <th>Статус</th>
                            <th>Действие</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="score" items="${scoreList}">
                            <tr>
                                <td data-label="Произведение">
                                    <a class="score-title" href="${pageContext.request.contextPath}/score/details?id=${score.scoreId}">
                                        <c:out value="${score.title}"/>
                                    </a>
                                    <a class="preview-link" href="${pageContext.request.contextPath}/download?scoreId=${score.scoreId}">Открыть PDF</a>
                                </td>
                                <td data-label="Загрузил">Пользователь #<c:out value="${score.userId}"/></td>
                                <td data-label="Композитор">#<c:out value="${score.composerId}"/></td>
                                <td data-label="Сложность"><c:out value="${score.difficulty}"/></td>
                                <td data-label="Загружено"><c:out value="${score.uploadedAt}"/></td>
                                <td data-label="Статус">
                                    <c:choose>
                                        <c:when test="${score.moderationStatus eq 'approved'}"><span class="status-badge status-approved">Одобрено</span></c:when>
                                        <c:when test="${score.moderationStatus eq 'rejected'}"><span class="status-badge status-rejected">Отклонено</span></c:when>
                                        <c:otherwise><span class="status-badge status-pending">На проверке</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td data-label="Действие">
                                    <form class="status-form" action="${pageContext.request.contextPath}/admin/moderate" method="post">
                                        <input type="hidden" name="scoreId" value="<c:out value='${score.scoreId}'/>">
                                        <select name="moderationStatus" aria-label="Новый статус для ${score.title}">
                                            <option value="pending" <c:if test="${score.moderationStatus eq 'pending'}">selected</c:if>>На проверке</option>
                                            <option value="approved" <c:if test="${score.moderationStatus eq 'approved'}">selected</c:if>>Одобрить</option>
                                            <option value="rejected" <c:if test="${score.moderationStatus eq 'rejected'}">selected</c:if>>Отклонить</option>
                                        </select>
                                        <button type="submit" class="save-button">Сохранить</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <div class="empty-state">
                    <span aria-hidden="true">♫</span>
                    <h3>Записей пока нет</h3>
                    <p>Когда пользователи загрузят ноты, они появятся здесь для проверки.</p>
                </div>
            </c:otherwise>
        </c:choose>
    </section>
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
