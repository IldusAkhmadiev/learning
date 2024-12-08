$(document).ready(function() {
    let results = {};
    const slides = $('.slide');
    let currentSlideIndex = 0;
    const lessonId = $("meta[name='lessonId']").attr("content");
    const userId = $("input[name='userId']").val(); // Получение значения из скрытого поля
    console.log(userId);
    function updateProgress() {
        const progressPercentage = (currentSlideIndex / (slides.length - 2)) * 100; // -2 чтобы игнорировать результативный слайд
        $('#progress').css('width', `${progressPercentage}%`);
        console.log(`Progress updated: ${progressPercentage}%`);
    }

    function showNextSlide() {
        console.log(`Hiding slide ${currentSlideIndex}`);
        $(slides[currentSlideIndex]).removeClass('active');
        currentSlideIndex++;
        if (currentSlideIndex < slides.length - 1) { // -1 чтобы игнорировать результативный слайд
            console.log(`Showing slide ${currentSlideIndex}`);
            $(slides[currentSlideIndex]).addClass('active');
            enableAnswerSelection(); // Разблокируем варианты ответов для следующего слайда
        } else {
            showFinalResults();
        }
        updateProgress();
    }

    function enableAnswerSelection() {
        const currentForm = $(slides[currentSlideIndex]).find('.translation-form');
        currentForm.find('input[name="answer"]').prop('disabled', false);
        currentForm.find('button[type="submit"]').text('Submit').off('click').on('click', submitAnswer).prop('disabled', false);
        $(slides[currentSlideIndex]).find('.feedback').empty();
        console.log(`Answers re-enabled for slide ${currentSlideIndex}`);
    }

    function showFinalResults() {
        $(slides[slides.length - 1]).addClass('active'); // Показываем последний слайд с результатами
        let correctCount = Object.values(results).filter(result => result).length;
        let totalQuestions = Object.keys(results).length;
        let resultPercent = Math.round((correctCount / totalQuestions) * 100);

        $('#total-result').html(`
            <p>Правильных ответов: ${correctCount} из ${totalQuestions}</p>
            <p>Результат: ${resultPercent}%</p>
        `);

        $.ajax({
            url: '/api/v1/learn/eng/practice/results',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                userId: userId,
                lessonId: lessonId,
                resultPercent: resultPercent,
                completedAt: new Date().toISOString()
            }),
            success: function(response) {
                console.log('Result saved successfully.');
            },
            error: function() {
                alert('Ошибка при сохранении результата.');
            }
        });

        console.log('Final results shown');
    }

    function submitAnswer(e) {
        e.preventDefault();
        const form = $(this).closest('form');
        const pronoun = form.data('pronoun');
        const selectedAnswer = form.find('input[name="answer"]:checked').val();
        const questionId = form.find('input[name="questionId"]').val();

        // Если ответ не выбран, не отправляем запрос
        if (!selectedAnswer) {
            alert('Пожалуйста, выберите ответ.');
            return;
        }

        $.ajax({
            url: '/api/v1/learn/eng/practice/' + lessonId + '/submit', // Динамический lessonId
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                questionId: questionId,
                question: pronoun,
                answer: selectedAnswer
            }),
            success: function(response) {
                // Блокируем варианты ответов
                form.find('input[name="answer"]').prop('disabled', true);

                // Показываем фидбэк
                form.siblings('.feedback').text(response.feedback);

                // Сохраняем результат
                results[pronoun] = response.correct;

                // Изменяем текст кнопки и добавляем обработчик для перехода к следующему слайду
                form.find('button[type="submit"]').text('Next').off('click').on('click', function(e) {
                    e.preventDefault();
                    showNextSlide();
                }).prop('disabled', false);

                console.log('Feedback shown and button text updated to Next');
            },
            error: function() {
                alert('Ошибка при проверке ответа.');
            }
        });
    }

    $('.translation-form').on('submit', submitAnswer);

    $('#restart-btn').on('click', function() {
        results = {};
        currentSlideIndex = 0;
        slides.removeClass('active');
        $(slides[0]).addClass('active');
        enableAnswerSelection(); // Разблокируем варианты ответа для первого слайда
        updateProgress();
    });

    // Начальная инициализация
    updateProgress();
    $(slides[0]).addClass('active'); // Убедимся, что первый слайд виден при загрузке страницы
    console.log('Initial slide shown');

    // Получаем кнопку по ее ID
    const restartButton = document.getElementById('go_home');

// Назначаем обработчик события для клика
    restartButton.addEventListener('click', () => {
        // Перенаправляем пользователя на указанный URL
        window.location.href = 'http://localhost:8080/learn/eng';
    });
});
