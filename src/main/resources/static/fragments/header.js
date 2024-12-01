document.addEventListener('DOMContentLoaded', () => {
    // Обработка клика по кнопке "Выйти"
    document.getElementById('logout-btn').addEventListener('click', () => {
        // Реализуйте логику выхода пользователя
        window.location.href = '/logout'; // Пример перенаправления на страницу выхода
    });
});
