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
    <section class="hero" id="home">
        <div class="hero-banner" role="img" aria-label="Баннер NoteShare с нотными линиями"></div>
        <div class="hero-content">
            <p class="eyebrow">Музыкальное пространство NoteShare</p>
            <h1>Музыка начинается здесь</h1>
            <p class="hero-description">Находите ноты, открывайте композиторов и делитесь музыкой.</p>

            <form class="search-form" action="score" method="get" role="search">
                <label class="visually-hidden" for="score-query">Название произведения или композитора</label>
                <input id="score-query" name="q" type="search" placeholder="Название произведения или композитора">
                <button type="submit">Искать ноты</button>
            </form>

            <a class="share-link" href="login">Войти, чтобы поделиться нотами <span aria-hidden="true">→</span></a>
        </div>
    </section>

    <section class="site-sections" aria-labelledby="sections-title">
        <div class="section-heading">
            <p class="eyebrow">Исследуйте NoteShare</p>
            <h2 id="sections-title">Всё для вашей музыки</h2>
            <p>Выберите раздел и продолжайте знакомство с музыкальными произведениями.</p>
        </div>

        <div class="section-cards">
            <a class="section-card" href="score">
                <span class="card-number">01</span>
                <span class="card-icon" aria-hidden="true">♫</span>
                <h3>Галерея нот</h3>
                <p>Перейдите в каталог и найдите нужное произведение.</p>
                <span class="card-arrow" aria-hidden="true">→</span>
            </a>

            <a class="section-card" href="composers">
                <span class="card-number">02</span>
                <span class="card-icon" aria-hidden="true">𝄞</span>
                <h3>Композиторы</h3>
                <p>Откройте страницу композиторов и их произведений.</p>
                <span class="card-arrow" aria-hidden="true">→</span>
            </a>

            <a class="section-card" href="register">
                <span class="card-number">03</span>
                <span class="card-icon" aria-hidden="true">✦</span>
                <h3>Делитесь музыкой</h3>
                <p>Создайте аккаунт, чтобы присоединиться к NoteShare.</p>
                <span class="card-arrow" aria-hidden="true">→</span>
            </a>
        </div>
    </section>

    <section class="catalog-empty" id="catalog" aria-labelledby="catalog-title">
        <span class="empty-decoration" aria-hidden="true">✧</span>
        <h2 id="catalog-title">Популярные ноты</h2>
        <p>Когда каталог будет наполнен, популярные произведения появятся здесь.</p>
        <a href="score">Перейти в галерею нот</a>
    </section>
</main>

<jsp:include page="/jsp/fragments/footer.jsp" />
</body>
</html>
