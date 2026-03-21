package frontend.components

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.`$$`
import io.qameta.allure.Step

/**
 * Компонент заголовка "Popular Products" на главной странице.
 */
class PopularProductsTitleComponent {

    private val titleElement = `$$`(".title").findBy(text("Popular Products"))

    @Step("Получить текст заголовка популярных товаров")
    fun getText(): String = titleElement.text()
}
