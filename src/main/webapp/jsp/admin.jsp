<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="UTF-8">
    <title>admin panel</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css">
</head>
<body>
    <c:if test="${not empty error}">
        <p class="form-error"><c:out value="${error}"/></p>
    </c:if>

    <form action="${pageContext.request.contextPath}/admin" method="post">
        <label>Название нот</label>
        <input type="text" name="title" maxlength="150" required><br><br>

        <label>Композитор</label>
        <input type="text" name="composer" maxlength="100" required><br><br>

        <label for="composer-difficulty">Сложность</label>
        <select id="composer-difficulty" name="diff" class="score-select" required>
            <option value="easy">easy</option>
            <option value="medium">medium</option>
            <option value="hard">hard</option>
        </select><br><br>

        <label>Жанр</label>
        <select id="genre" name="genre" class="score-select">
            <c:forEach var="genre" items="${genres}">
                <option value="${genre.name}">${genre.name}</option>
            </c:forEach>
        </select><br><br>

        <label for="score-file">Файл нот (только PDF)</label>
        <input id="score-file" name="scoreFile" type="file" accept=".pdf,application/pdf" required><br><br>

        <button value="createScore" type="submit" name="action"></button>
    </form>

    <form action="${pageContext.request.contextPath}/admin" method="post">

        <label>Введите ID нот</label>
        <input type="text" name="scoreId">

        <button value="deleteScore" type="submit" name="action"></button>
    </form>

    <c:forEach var="score" items="${scoresPending}">

        <form action="${pageContext.request.contextPath}/admin" method="post">
            <input type="hidden" name="scoreId" value="${score.scoreId}">
            <span>${score.title}</span>
            <select name="status">
                <option value="pending" ${score.moderationStatus == 'pending' ? 'selected' : ''}>Pending</option>
                <option value="approved" ${score.moderationStatus == 'approved' ? 'selected' : ''}>approved</option>
                <option value="rejected" ${score.moderationStatus == 'rejected' ? 'selected' : ''}>rejected</option>
            </select>
            <a href="${pageContext.request.contextPath}/assets/scores/${score.filePath}" download="">Скачать PDF</a>

            <button type="submit" value="updateStatus" name="action"></button>
        </form>
    </c:forEach>
</body>