# XLSX Number Finder Service

## 📌 Описание
REST API-сервис на **Spring Boot** для поиска **N-го максимального числа** из XLSX-файла. Файл содержит целые числа в столбик. Для документации используется **Swagger**.

---
## 🛠️ Сборка и запуск

### Предварительные требования

Перед запуском убедитесь, что у вас установлены:

- **Docker**
- **Docker Compose**
- **Git**

### 📥 Клонирование проекта:
```bash
git clone https://github.com/aleksandrbratchin/xlsxnumberfinder.git
cd xlsxnumberfinder
```

### 🐳 Запуск через Docker Compose:
```bash
docker-compose up --build
```
Сервис будет доступен по адресу: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---
## 📡 API Пример запроса:
**Метод:** `GET /api/nth-max`
**Пример:**
```bash
curl 'http://localhost:8080/api/nth-max?filePath=/app/files/file.xlsx&n=3'
```
**Ответ:**
```string
  42
```
---
## 📝 Docker Compose Конфигурация:
```yaml
version: '3.5'
services:
  xlsx_number_finder_dev:
    build:
      context: .
      dockerfile: Dockerfile
    container_name: xlsx_number_finder_dev
    ports:
      - "8080:8080"
    volumes:
      - D:/files:/app/files
    restart: unless-stopped
```
---
## 📘 Swagger-документация:
- Доступна по адресу: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## 📂 Структура проекта:
- **controller:** `NumberFinderController` — обработка запроса `/nth-max`.
- **service:** `XlsxReaderService`, `NthMaxFinderService` — логика чтения и поиска.
- **exception:** `GlobalExceptionHandler` — глобальная обработка ошибок.
- **Dockerfile**, `docker-compose.yml` — контейнеризация.

## ✅ Тестирование:
Используются **JUnit 5** и **AssertJ**:

## 💡 Примечания:
- Работа с локальными файлами обеспечена через Docker volume. При запуске Docker Compose укажите свою папку в пункте volumes

✨ **Автор:** [Братчин Александр](https://github.com/aleksandrbratchin)

