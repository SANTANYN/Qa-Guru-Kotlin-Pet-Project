# Backend API tests

HTTP-тесты к Ktor API (`/api/v1/...`) через Retrofit + Gson. Исходники: каталог [`src/test/kotlin/backend/`](.) — пакет Kotlin `backend.*` (корень тестов Kotlin — `src/test/kotlin`, без дублирования имени в пути).

## Что нужно перед прогоном

1. Поднять **БД** и **backend** (порт **1111**). В `docker-compose` для backend задано `PRESET: "true"`, но пресет вставляется **только в пустую БД** (`DatabaseHelper.generatePresetData`: если в таблицах уже есть строки — вставка пропускается). Старый **volume** Postgres может оставить БД без `user@autotest.com` — тогда **[LoginTest](backend/LoginTest.kt)** упадёт на логине, хотя сервер поднят. Варианты: `docker compose down -v` и поднять снова, либо вручную завести пользователя с теми же кредами.
2. **[CreateUserTest](backend/CreateUserTest.kt)** позитивный и тест дубликата **не требуют** пресета: сначала создаётся пользователь через публичный `POST /users/create`, затем логин под ним для GET/DELETE.
3. Базовый URL: `System.getProperty("api.base.url")` — по умолчанию из Gradle `http://localhost:1111`. Клиент сам добавляет суффикс `/api/v1/`.

Пример:

```bash
make run-backend
# или: cd src/main/backend && docker compose up -d
```

## Запуск только API-тестов

```bash
./gradlew test --tests 'backend.LoginTest' --tests 'backend.CreateUserTest'
```

Или все тесты пакета `backend`:

```bash
./gradlew test --tests 'backend.*'
```

Переопределение URL:

```bash
./gradlew test --tests 'backend.*' -Dapi.base.url=http://localhost:1111
```

## Структура

- [`backend/api/extension/Extensions.kt`](backend/...) — `getAsObject()` / `getErrorAsObject()` (разбор ошибок Gson в одном месте).
- [`backend/controllers`](backend/controllers) — `AuthController` / `UsersController` с `execute()` внутри методов.
- [`backend/api/models`](backend/api/models) — DTO и эталонные ошибки (`AuthErrors`, `CreateUserErrors`) для сравнения `error shouldBe expected`.
- [`backend/helpers`](backend/helpers) — тестовые фикстуры (например [`PresetUsers.kt`](backend/helpers/PresetUsers.kt) для `LoginTest`), а не бизнес-код приложения.
