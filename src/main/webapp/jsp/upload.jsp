<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Загрузка нот — NoteShare</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/upload.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
</head>
<body>
<jsp:include page="/jsp/fragments/header.jsp" />

<main class="upload-page">
    <section class="page-heading">
        <p class="eyebrow">Поделитесь музыкой</p>
        <h1>Загрузить ноты</h1>
        <p>Добавьте произведение в галерею NoteShare.</p>
    </section>

    <c:if test="${not empty error}">
        <p class="form-error"><c:out value="${error}"/></p>
    </c:if>

    <form class="upload-form" action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
        <label for="score-title">Название произведения</label>
        <input id="score-title" name="title" type="text" autocomplete="off"
               maxlength="150" value="<c:out value='${title}'/>" required>

        <label for="composer-name">Композитор</label>
        <input id="composer-name" name="composerName" type="text" autocomplete="off"
               maxlength="100" value="<c:out value='${composerName}'/>" required>

        <label for="score-difficulty">Сложность</label>
        <select id="score-difficulty" name="diff" class="score-select" required>
            <c:forEach var="difficulty" items="${difficulties}">
                <option value="${difficulty.value}" ${diff == difficulty.value ? 'selected' : ''}>
                    <c:out value="${difficulty.label}"/>
                </option>
            </c:forEach>
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
        <input id="score-file" name="scoreFile" type="file" accept=".pdf,application/pdf" required>

        <button type="submit">Загрузить ноты</button>
    </form>
</main>

<jsp:include page="/jsp/fragments/footer.jsp" />
</body>
</html>
