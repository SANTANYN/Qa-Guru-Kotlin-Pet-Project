package frontend.components

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.`$$`

/**
 * Компонент заголовка "Popular Products" на главной странице.
 */
class PopularProductsTitleComponent {

    private val titleElement = `$$`(".title").findBy(text("Popular Products"))

    fun checkText(expectedText: String) {
        titleElement.shouldHave(text(expectedText))
    }
}
