package frontend.components

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.`$$`
import io.qameta.allure.Step

/**
 * Компонент заголовка "Popular Products" на главной странице.
 */
class PopularProductsTitleComponent {

    private val titleElement = `$$`(".title").findBy(text("Popular Products"))

    @Step("Проверить текст заголовка популярных товаров: {expectedText}")
    fun checkText(expectedText: String) {
        titleElement.shouldHave(text(expectedText))
    }
}
