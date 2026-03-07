package frontend.extensions

import com.codeborne.selenide.Condition
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.SelenideElement

/**
 * Ищет элемент в коллекции по data-test-id.
 * Если элемент не найден — падает с понятным сообщением.
 */
fun ElementsCollection.findByTestIdOrFail(testId: String): SelenideElement {
    val filtered = filterBy(Condition.attribute("data-test-id", testId))
    if (filtered.size() == 0) {
        throw AssertionError(
            "Элемент с data-test-id='$testId' не найден в коллекции. " +
                "Доступно элементов: ${size()}. " +
                "Проверьте, что элемент отображается на странице."
        )
    }
    return filtered.get(0)
}

/**
 * Возвращает элемент по индексу.
 * Если индекс вне диапазона — падает с понятным сообщением.
 */
fun ElementsCollection.elementAtOrFail(index: Int): SelenideElement {
    if (index < 0) {
        throw AssertionError("Индекс не может быть отрицательным: $index")
    }
    if (index >= size()) {
        throw AssertionError(
            "Элемент с индексом $index не найден в коллекции. " +
                "Доступно элементов: ${size()} (индексы 0..${(size() - 1).coerceAtLeast(0)})."
        )
    }
    return get(index)
}
