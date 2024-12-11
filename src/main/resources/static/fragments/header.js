document.addEventListener('DOMContentLoaded', () => {
    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    document.getElementById('logout-link').addEventListener('click', (event) => {
        event.preventDefault(); // Отменяем стандартное действие

        fetch('/logout', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                [csrfHeader]: csrfToken // Передача CSRF-токена в заголовке
            }
        }).then(response => {
            if (response.ok) {
                window.location.href = '/login?logout'; // Перенаправление после успешного выхода
            } else {
                console.error('Ошибка при выполнении logout');
            }
        }).catch(error => console.error('Ошибка:', error));
    });
});



// без csrf
// document.addEventListener('DOMContentLoaded', () => {
//     // Обработка клика по кнопке "Выйти"
//     document.getElementById('logout-btn').addEventListener('click', () => {
//         // Реализуйте логику выхода пользователя
//         window.location.href = '/logout'; // Пример перенаправления на страницу выхода
//     });
// });
