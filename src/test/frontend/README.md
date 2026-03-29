# Frontend Tests

## data-test-id и data-test-group на сайте

Сайт использует атрибуты для стабильных селекторов в автотестах:

- **data-test-id** — уникальный идентификатор конкретного элемента или экземпляра (часто с суффиксом id сущности, например `product-card-42`). Так тесты не привязаны к классам и вёрстке: редизайн и рефакторинг стилей не ломают локаторы. Примеры в коде: навигация в `src/main/web/src/components/Header.vue`, заголовок списка в `ProductsView.vue`.

- **data-test-group** — общая метка для **повторяющихся** блоков одного типа. По ней получают коллекцию (`$$`), затем фильтруют по `data-test-id`, по индексу или по условию. Примеры: карточки товаров в [`ProductItems.vue`](../../main/web/src/components/ProductItems.vue) (`product-card`, `product-card-name`, …), строки корзины в [`CartItems.vue`](../../main/web/src/components/CartItems.vue) (`cart-item`, `cart-item-qty`, …).

В тестах обёртки: [`Wrappers.byDataTestId`](helpers/Wrappers.kt) / [`byDataTestGroup`](helpers/Wrappers.kt).

## Ресурсы тестов (`src/test/frontend/resources`)

Тестовые ресурсы (в т.ч. `META-INF/services` для JUnit `TestExecutionListener`) лежат рядом с фронтенд-тестами. В `build.gradle` для `sourceSets.test` задано `resources.srcDirs = ["src/test/frontend/resources"]`.
