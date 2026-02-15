# Qa-Guru-Kotlin-Pet-Project

Тестовый фреймворк на Kotlin с backend (Ktor) и frontend (Vue/Vite) для тестирования.

## Требования

- Java 17+
- Node.js 18+ (для сборки frontend)
- Docker и Docker Compose
- Make (опционально, для удобных команд)

## Первоначальная настройка

```bash
# Клонировать с submodules
git clone --recurse-submodules <repo-url>
cd Qa-Guru-Kotlin-Pet-Project

# Или инициализировать submodules после клонирования
make init
```

## Сборка

```bash
# Собрать всё
make build

# Собрать только backend
make build-backend

# Собрать только frontend
make build-frontend
```

## Запуск

```bash
# Запустить backend и frontend (Docker)
make run

# Запустить только backend (порт 1111)
make run-backend

# Запустить только frontend (порт 4000)
make run-frontend

# Остановить контейнеры и запустить заново
make restart
```

После запуска:
- Backend: http://localhost:1111
- Frontend: http://localhost:4000

## Остановка

```bash
# Остановить все контейнеры
make stop
# или
make down
```

## Тесты

```bash
# Запустить тесты
make test
```

## Команды Makefile

| Команда | Описание |
|---------|----------|
| `make init` | Инициализировать submodules |
| `make build` | Собрать backend и frontend |
| `make run` | Запустить backend и frontend (Docker) |
| `make restart` | Остановить и запустить заново |
| `make stop` | Остановить контейнеры |
| `make test` | Запустить тесты |
