-- Таблица для Users
CREATE TABLE users (
                       id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                       email VARCHAR(100) NULL,
                       username VARCHAR(100) NULL
);

-- Таблица для Words
CREATE TABLE word (
                       id BIGSERIAL PRIMARY KEY,
                       original VARCHAR(100) NOT NULL,
                       transcription VARCHAR(100) NOT NULL,
                       part_of_speech VARCHAR(100) NOT NULL
);

-- Таблица для User Roles
CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL,
                            roles VARCHAR(100) NULL,
                            PRIMARY KEY (user_id, roles),
                            FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Таблица для Lessons (должна быть раньше questions)
CREATE TABLE lesson (
                        id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                        language VARCHAR(70) NOT NULL,
                        lesson_number INT NOT NULL,
                        title VARCHAR(100) NOT NULL
);

-- Таблица для Questions
CREATE TABLE question (
                           id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                           lesson_id INT NOT NULL,
                           text TEXT NOT NULL,
                           question_type VARCHAR(70) NOT NULL,
                           FOREIGN KEY (lesson_id) REFERENCES lesson(id)
);

-- Таблица для Answers
CREATE TABLE answer (
                         id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                         question_id INT NOT NULL,
                         text TEXT NOT NULL,
                         correct BOOLEAN NOT NULL,
                         feedback TEXT,
                         FOREIGN KEY (question_id) REFERENCES question(id)
);

-- Таблица для User Lesson Results
CREATE TABLE user_lesson_results (
                                     id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                                     user_id VARCHAR(255) NOT NULL,
                                     lesson_id BIGINT NOT NULL,
                                     result_percent DOUBLE PRECISION NOT NULL,
                                     completed_at TIMESTAMP NOT NULL
);

-- Таблица для Translations
CREATE TABLE translation (
                              id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                              word_id BIGINT NOT NULL,
                              language VARCHAR(50) NOT NULL,
                              translation VARCHAR(100) NOT NULL,
                              context_original TEXT NOT NULL,
                              context_translated TEXT NOT NULL,
                              FOREIGN KEY (word_id) REFERENCES word(id)
);