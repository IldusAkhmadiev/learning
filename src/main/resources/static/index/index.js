document.addEventListener('DOMContentLoaded', () => {
    const userList = document.getElementById('user-list').querySelectorAll('td');

    userList.forEach((user, index) => {
        setTimeout(() => {
            user.classList.remove('hidden');
        }, index * 300); // задержка для появления пользователей по очереди
    });
});
