# Frontend Tests

## data-test-id и data-test-group на сайте

Сайт использует атрибуты для стабильных селекторов в автотестах:

- **data-test-id** — уникальный идентификатор конкретного элемента или экземпляра (часто с суффиксом id сущности, например `product-card-42`). Так тесты не привязаны к классам и вёрстке: редизайн и рефакторинг стилей не ломают локаторы. Примеры в коде: навигация в `src/main/web/src/components/Header.vue`, заголовок списка в `ProductsView.vue`.

- **data-test-group** — общая метка для **повторяющихся** блоков одного типа. По ней получают коллекцию (`$$`), затем фильтруют по `data-test-id`, по индексу или по условию. Примеры: карточки товаров в [`ProductItems.vue`](../../../main/web/src/components/ProductItems.vue) (`product-card`, `product-card-name`, …), строки корзины в [`CartItems.vue`](../../../main/web/src/components/CartItems.vue) (`cart-item`, `cart-item-qty`, …).

В тестах обёртки: [`Wrappers.byDataTestId`](helpers/Wrappers.kt) / [`byDataTestGroup`](helpers/Wrappers.kt).

## Ресурсы тестов (`src/test/kotlin/frontend/resources`)

Тестовые ресурсы (в т.ч. `META-INF/services` для JUnit `TestExecutionListener`) лежат рядом с фронтенд-тестами. Корень Kotlin-тестов — `src/test/kotlin`; в `build.gradle` задано `resources.srcDirs = ["src/test/kotlin/frontend/resources"]`.

## Конфигурация тестов (properties / JSON) и `System.getProperty`

Загрузка выполняется в [TestConfig](config/TestConfig.kt) при первом вызове `TestConfig.get()` / [Config.get()](config/Config.kt) (тонкая обёртка «как на скрине» курса; listener вызывает `Config.get()`). Первый вызов — в [TestListener](listeners/TestListener.kt) при старте плана.

**Отличие от эталона лекции:** на занятиях часто один ключ `System.getProperty("env_config", "/example.properties")` и класс `Properties`; здесь основные ключи — `frontend.test.json` / `frontend.test.properties`, плюс **алиас** `env_config` с тем же смыслом (путь к `.properties` на classpath), чтобы совпадать с разбором ДЗ и скринами.

**Приоритет источника:**

1. `frontend.test.json` — путь к JSON на classpath, например `/config/frontend-test.json`
2. иначе `frontend.test.properties` — путь к `.properties` на classpath
3. иначе `env_config` — путь к `.properties` на classpath (как на лекции)
4. иначе дефолт: [`config/frontend-test.properties`](resources/config/frontend-test.properties)

**Примеры Gradle:**

```bash
./gradlew test -Dfrontend.test.properties=/config/frontend-test.properties
./gradlew test -Dfrontend.test.json=/config/frontend-test.json
./gradlew test -Denv_config=/config/frontend-test.properties
```

Ключи в `.properties`: `selenide.baseUrl`, `screenshots.on.failure` (`true`/`false`), `verbose.logging`.  
JSON: поля `selenideBaseUrl`, `attachScreenshotsOnFailure`, `verboseLogging` (см. [`frontend-test.json`](resources/config/frontend-test.json)).

После загрузки listener выставляет `System.setProperty("selenide.baseUrl", ...)` и `Configuration.baseUrl`.

## `TestExecutionListener` и `TestWatcher`

- Глобальный listener: [TestListener.kt](listeners/TestListener.kt), регистрация через `META-INF/services/org.junit.platform.launcher.TestExecutionListener`. Старт каждого теста логируется всегда (`|--- Test started: …`); расширенный вывод — при `verbose.logging=true`. Скриншот в Allure при падении не делается для псевдо-теста с `displayName == "JUnit Jupiter"` (как на скрине курса); вложение — `@Attachment(value = "{name}", …)` с именем по умолчанию `SCREENSHOT`.
- [TestWatcherExtension.kt](listeners/TestWatcherExtension.kt) — демо Jupiter `TestWatcher`; подключение `@ExtendWith(TestWatcherExtension::class)`, минимальный пример в [PropertiesTest.kt](PropertiesTest.kt) (класс `ConfigAndWatcherDemoTest`: один позитивный тест на `Config.get()` + `env_config`; при прохождении срабатывает `testSuccessful`).
