<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="theme-color" content="#064E3B">
    <title>Админ-панель — NoteShare</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
</head>
<body>
<jsp:include page="/jsp/fragments/header.jsp" />

<main class="admin-page">
    <div class="admin-shell">
        <header class="admin-heading">
            <div>
                <p class="admin-eyebrow">NoteShare · управление каталогом</p>
                <h1>Панель администратора</h1>
                <p>Добавляйте произведения и проверяйте отправленные ноты.</p>
            </div>
            <span class="admin-heading-mark" aria-hidden="true">𝄞</span>
        </header>

        <c:if test="${not empty error}">
            <div class="admin-alert" role="alert"><c:out value="${error}"/></div>
        </c:if>

        <section class="admin-tools" aria-label="Управление произведениями">
            <form class="admin-form create-form" action="${pageContext.request.contextPath}/admin" method="post" enctype="multipart/form-data">
                <div class="form-heading">
                    <span class="form-mark" aria-hidden="true">♫</span>
                    <div>
                        <h2>Добавить ноты</h2>
                        <p>Заполните информацию о произведении.</p>
                    </div>
                </div>

                <label for="score-title">Название произведения</label>
                <input id="score-title" type="text" name="title" maxlength="150" required>

                <label for="composer-name">Композитор</label>
                <input id="composer-name" type="text" name="composer" maxlength="100" required>

                <label for="score-difficulty">Сложность</label>
                <select id="score-difficulty" name="diff" class="admin-select" required>
                    <option value="easy">Лёгкая</option>
                    <option value="medium">Средняя</option>
                    <option value="hard">Сложная</option>
                </select>

                <fieldset class="genre-options">
                    <legend>Жанры</legend>
                    <c:forEach var="genre" items="${genres}">
                        <label class="genre-option">
                            <input type="checkbox" name="genre" value="${genre.name}">
                            <c:out value="${genre.name}"/>
                        </label>
                    </c:forEach>
                </fieldset>

                <label for="score-file">Файл нот (только PDF)</label>
                <input id="score-file" class="file-input" name="scoreFile" type="file" accept=".pdf,application/pdf" required>

                <button class="admin-button admin-button--primary" type="submit" name="action" value="createScore">Добавить произведение</button>
            </form>

            <form class="admin-form delete-form" action="${pageContext.request.contextPath}/admin" method="post">
                <div class="form-heading">
                    <span class="form-mark form-mark--muted" aria-hidden="true">⌫</span>
                    <div>
                        <h2>Удалить ноты</h2>
                        <p>Укажите ID произведения из каталога.</p>
                    </div>
                </div>
                <label for="delete-score-id">ID произведения</label>
                <input id="delete-score-id" type="number" name="scoreId" min="1" required>
                <button class="admin-button admin-button--danger" type="submit" name="action" value="deleteScore">Удалить произведение</button>
            </form>
        </section>

        <section class="moderation-section" aria-labelledby="moderation-title">
            <div class="moderation-heading">
                <div>
                    <p class="admin-eyebrow">Проверка публикаций</p>
                    <h2 id="moderation-title">Модерация нот</h2>
                </div>
                <span class="moderation-count">Ожидают решения</span>
            </div>

            <c:choose>
                <c:when test="${not empty scoresPending}">
                    <div class="moderation-list">
                        <c:forEach var="score" items="${scoresPending}">
                            <form class="moderation-card" action="${pageContext.request.contextPath}/admin" method="post">
                                <input type="hidden" name="scoreId" value="${score.scoreId}">
                                <div class="moderation-card__title">
                                    <span class="moderation-note" aria-hidden="true">♫</span>
                                    <h3><c:out value="${score.title}"/></h3>
                                </div>
                                <label for="status-${score.scoreId}">Статус произведения</label>
                                <select id="status-${score.scoreId}" name="status" class="admin-select">
                                    <option value="pending" ${score.moderationStatus == 'pending' ? 'selected' : ''}>На проверке</option>
                                    <option value="approved" ${score.moderationStatus == 'approved' ? 'selected' : ''}>Одобрено</option>
                                    <option value="rejected" ${score.moderationStatus == 'rejected' ? 'selected' : ''}>Отклонено</option>
                                </select>
                                <div class="moderation-actions">
                                    <a class="download-link" href="${pageContext.request.contextPath}/assets/scores/${score.filePath}" download>Скачать PDF</a>
                                    <button class="admin-button admin-button--small" type="submit" name="action" value="updateStatus">Сохранить</button>
                                </div>
                            </form>
                        </c:forEach>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="admin-empty">
                        <span aria-hidden="true">✦</span>
                        <p>Сейчас нет нот, ожидающих проверки.</p>
                    </div>
                </c:otherwise>
            </c:choose>
        </section>
    </div>
</main>

<jsp:include page="/jsp/fragments/footer.jsp" />
</body>
</html>
