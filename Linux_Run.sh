#!/bin/bash

# Проверяем и останавливаем существующие контейнеры
echo "Checking and cleaning up existing containers..."
docker stop postgres-learning learning-app 2>/dev/null
docker rm postgres-learning learning-app 2>/dev/null

# Создаем сеть если её нет
docker network create app-network 2>/dev/null

# Запускаем PostgreSQL
echo "Starting PostgreSQL..."
docker run -d --name postgres-learning \
  --network app-network \
  -e POSTGRES_DB=learning \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=Tw2dj4dis5D \
  postgres:15-alpine

# Ждем 10 секунд, чтобы PostgreSQL успел запуститься
echo "Waiting for PostgreSQL to start..."
sleep 10

# Запускаем основное приложение
echo "Starting Learning App..."
docker run -d --name learning-app \
  --network app-network \
  -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://postgres-learning:5432/learning \
  -e SPRING_DATASOURCE_USERNAME=postgres \
  -e SPRING_DATASOURCE_PASSWORD=Tw2dj4dis5D \
  ildusakhmadiev/learning-app:1.0-light

echo
echo "Application is starting at http://localhost:8080"
echo
echo "To view logs use: docker logs learning-app"
echo "To stop application use: docker stop postgres-learning learning-app"
read -p "Press Enter to continue..."