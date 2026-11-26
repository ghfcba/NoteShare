<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Загрузка нот — NoteShare</title>
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/upload.css">
    <link rel="stylesheet" href="css/footer.css">
</head>
<body>
<jsp:include page="/jsp/fragments/header.jsp" />

<main class="upload-page">
    <section class="page-heading">
        <p class="eyebrow">Поделитесь музыкой</p>
        <h1>Загрузить ноты</h1>
        <p>Добавьте произведение в галерею NoteShare.</p>
    </section>

    <form class="upload-form" action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
        <label for="score-title">Название произведения</label>
        <input id="score-title" name="title" type="text" autocomplete="off" required>

        <label for="composer-name">Композитор</label>
        <input id="composer-name" name="composerName" type="text" autocomplete="off" required>

        <label for="composer-name">Сложность</label>
        <input id="composer-name" name="diff" type="text" autocomplete="off" required>

        <label for="score-file">Файл нот (только PDF)</label>
        <input id="score-file" name="scoreFile" type="file" accept=".pdf,application/pdf" required>

        <button type="submit">Загрузить ноты</button>
    </form>
</main>

<jsp:include page="/jsp/fragments/footer.jsp" />
</body>
</html>
