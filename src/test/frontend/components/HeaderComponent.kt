package frontend.components

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.`$$`
import frontend.extensions.findByOrFail
import frontend.helpers.Wrappers.Companion.byDataTestGroup
import frontend.helpers.Wrappers.Companion.byDataTestId
import io.qameta.allure.Step

/**
 * Компонент навигационной шапки.
 * Локаторы и взаимодействие с элементами хедера.
 */
class HeaderComponent {

    private val logoLink = `$`(byDataTestId("nav-link-home"))
    private val titleText = `$`(".header .title") // "Brew & Bean"
    private val linksHeader = `$$`(byDataTestGroup("nav-link"))
    private val cartButton = `$`(byDataTestId("nav-link-cart"))
    private val joinButton = `$`(byDataTestId("nav-link-auth"))

    @Step("Проверить текст заголовка в хедере: {expectedText}")
    fun checkTitleText(expectedText: String) {
        titleText.shouldHave(text(expectedText))
    }

    @Step("Нажать на логотип")
    fun clickLogo() {
        logoLink.click()
    }

    @Step("Нажать на ссылку '{linkName}' в хедере")
    fun clickLink(linkName: String) {
        linksHeader.findByOrFail(text(linkName), "Ссылка навигации '$linkName' не найдена в хедере!").click()
    }

    fun getLinksTexts(): List<String> = linksHeader.texts()

    @Step("Нажать на кнопку корзины")
    fun clickCart() {
        cartButton.click()
    }

    @Step("Нажать на кнопку Join")
    fun clickJoin() {
        joinButton.click()
    }
}
