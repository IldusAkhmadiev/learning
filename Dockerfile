# Многоступенчатая сборка для эффективного создания образа
# Этап 1: Сборка приложения
FROM gradle:8.5-jdk17-alpine AS build

# Установка рабочей директории
WORKDIR /app

# Копирование файлов сборки Gradle
COPY build.gradle settings.gradle ./
COPY gradle ./gradle

# Копирование исходного кода
COPY src ./src

# Перед RUN gradle clean build добавьте
RUN gradle --refresh-dependencies
# Сборка проекта без запуска тестов
RUN gradle clean build -x test --no-daemon --stacktrace

# Этап 2: Создание легковесного образа среды выполнения
FROM openjdk:17-slim

# Установка runtime зависимостей
RUN apt-get update && apt-get install -y \
    postgresql-client \
    && rm -rf /var/lib/apt/lists/*

# Установка рабочей директории
WORKDIR /app

# Копирование собранного JAR-файла
COPY --from=build /app/build/libs/*.jar app.jar

# Открытие портов для приложения и удаленной отладки
EXPOSE 8080 5005

# Установка переменных окружения для подключения к PostgreSQL
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5433/learning
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=Tw2dj4dis5D

# Проверка здоровья
HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Запуск приложения с включенной удаленной отладкой
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "/app/app.jar"]