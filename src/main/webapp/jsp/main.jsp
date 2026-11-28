<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="theme-color" content="#064E3B">
    <title>NoteShare — обмен нотами</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
</head>
<body>
<jsp:include page="/jsp/fragments/header.jsp" />

<main>
    <section class="site-sections" aria-label="Разделы NoteShare">
        <div class="section-heading">
            <p class="eyebrow">Музыкальное пространство NoteShare</p>
            <h1>Музыка начинается здесь</h1>
            <span class="heading-ornament" aria-hidden="true">✦</span>
            <p class="section-intro">Находите ноты, открывайте композиторов и делитесь музыкой.</p>
        </div>
        <div class="section-cards">
            <a class="section-card" href="score">
                <span class="card-number">01</span>
                <span class="card-icon" aria-hidden="true">♫</span>
                <h3>Галерея нот</h3>
                <p>Перейдите в каталог и найдите нужное произведение.</p>
            </a>

            <a class="section-card" href="composers">
                <span class="card-number">02</span>
                <span class="card-icon" aria-hidden="true">𝄞</span>
                <h3>Композиторы</h3>
                <p>Откройте страницу композиторов и их произведений.</p>
            </a>

            <a class="section-card" href="register">
                <span class="card-number">03</span>
                <span class="card-icon" aria-hidden="true">✦</span>
                <h3>Делитесь музыкой</h3>
                <p>Создайте аккаунт, чтобы присоединиться к NoteShare.</p>
            </a>
        </div>
    </section>
</main>

<jsp:include page="/jsp/fragments/footer.jsp" />
<script src="${pageContext.request.contextPath}/assets/js/main.js" defer></script>
</body>
</html>
