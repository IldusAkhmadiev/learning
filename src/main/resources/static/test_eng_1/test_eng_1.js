$(document).ready(function () {
    let results = {}; // Хранение результатов
    const slides = $('.slide'); // Все слайды
    let currentSlideIndex = 0; // Индекс текущего слайда

    // Функция обновления прогресс-бара
    function updateProgress() {
        const progressPercentage = (currentSlideIndex / (slides.length - 1)) * 100;
        $('#progress').css('width', `${progressPercentage}%`);
    }

    // Функция отображения следующего слайда
    function showNextSlide() {
        $(slides[currentSlideIndex]).removeClass('active'); // Скрываем текущий слайд
        currentSlideIndex++; // Переход к следующему слайду
        if (currentSlideIndex < slides.length) {
            $(slides[currentSlideIndex]).addClass('active'); // Показываем следующий слайд
            updateProgress();
        }
    }

    // Обработка отправки формы
    $('.translation-form').on('submit', function (e) {
        e.preventDefault(); // Отключаем стандартное поведение формы
        const pronoun = $(this).data('pronoun');
        const selectedAnswer = $(this).find('input[name="answer"]:checked').val();

        // Отправка ответа на сервер
        $.ajax({
            url: '/learn/eng/practice/submit',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ pronoun, answer: selectedAnswer }),
            success: function (response) {
                results[pronoun] = response.correct; // Сохраняем результат
                const feedbackEl = $(e.target).siblings('.feedback');
                feedbackEl.text(response.feedback).css('color', response.correct ? 'green' : 'red');

                // Автоматический переход к следующему слайду через 1.5 сек
                setTimeout(() => {
                    if (currentSlideIndex === slides.length - 2) {
                        showFinalResults(); // Если это последний вопрос
                    }
                    showNextSlide();
                }, 1500);
            },
            error: function () {
                alert('Ошибка при проверке ответа');
            }
        });
    });

    // Отображение результатов на последнем слайде
    function showFinalResults() {
        const correctCount = Object.values(results).filter(Boolean).length;
        const totalQuestions = Object.keys(results).length;
        $('#total-result').html(`
            <p>Правильных ответов: ${correctCount} из ${totalQuestions}</p>
            <p>Результат: ${Math.round((correctCount / totalQuestions) * 100)}%</p>
        `);
    }

    // Кнопка для перезапуска
    $('#restart-btn').on('click', function () {
        results = {}; // Сброс результатов
        currentSlideIndex = 0; // Сбрасываем индекс
        slides.removeClass('active'); // Скрываем все слайды
        $(slides[0]).addClass('active'); // Показываем первый слайд
        $('.feedback').empty(); // Очищаем фидбэк
        updateProgress(); // Обновляем прогресс
    });

    // Обновляем прогресс-бар при загрузке
    updateProgress();
});