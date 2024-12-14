document.addEventListener('DOMContentLoaded', () => {
    const userList = document.getElementById('user-list');

    if (userList) {
        const users = userList.querySelectorAll('td');

        users.forEach((user, index) => {
            setTimeout(() => {
                user.style.opacity = 1;
                user.style.transform = 'translateY(0)';
            }, index * 300);
        });
    }
});