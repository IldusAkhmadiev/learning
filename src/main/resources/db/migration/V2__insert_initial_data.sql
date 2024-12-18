-- Вставка данных
INSERT INTO users (email, username) VALUES ('anan308an@gmail.com', 'An Ane');

INSERT INTO user_roles (user_id, roles) VALUES (1, 'ADMIN');

INSERT INTO word (original, transcription, part_of_speech) VALUES
                                                                ('we', 'wiː', 'PRONOUN'),
                                                                ('I', 'aɪ', 'PRONOUN'),
                                                                ('you', 'juː', 'PRONOUN'),
                                                                ('he', 'hiː', 'PRONOUN'),
                                                                ('she', 'ʃiː', 'PRONOUN'),
                                                                ('it', 'ɪt', 'PRONOUN'),
                                                                ('they', 'ðeɪ', 'PRONOUN');

INSERT INTO lesson (language, lesson_number, title) VALUES ('English', 1, 'Pronoun');

INSERT INTO translation (word_id, language, translation, context_original, context_translated) VALUES
                                                                                                    (1, 'ru', 'мы', 'We were just chatting about what we did last weekend.', 'Мы просто болтали о том, что делали на прошлых выходных.'),
                                                                                                    (2, 'ru', 'я', 'I am learning English.', 'Я изучаю английский.'),
                                                                                                    (3, 'ru', 'ты, вы', 'You look great today!', 'Ты (вы) отлично выглядишь(те) сегодня!'),
                                                                                                    (4, 'ru', 'он', 'He is reading a book.', 'Он читает книгу.'),
                                                                                                    (5, 'ru', 'она', 'She loves to cook.', 'Она любит готовить.'),
                                                                                                    (6, 'ru', 'оно, это', 'It is raining outside.', 'На улице идёт дождь.'),
                                                                                                    (7, 'ru', 'они', 'They are going to the park.', 'Они идут в парк.');

INSERT INTO question (lesson_id, text, question_type) VALUES
                                                           (1, 'we', 'PRONOUN'),
                                                           (1, 'I', 'PRONOUN'),
                                                           (1, 'you', 'PRONOUN'),
                                                           (1, 'he', 'PRONOUN'),
                                                           (1, 'she', 'PRONOUN'),
                                                           (1, 'it', 'PRONOUN'),
                                                           (1, 'they', 'PRONOUN');

INSERT INTO answer (question_id, text, correct, feedback) VALUES
                                                               (1, 'мы', true, ''),
                                                               (2, 'я', true, ''),
                                                               (3, 'ты, вы', true, ''),
                                                               (4, 'он', true, ''),
                                                               (5, 'она', true, ''),
                                                               (6, 'оно, это', true, ''),
                                                               (7, 'они', true, '');