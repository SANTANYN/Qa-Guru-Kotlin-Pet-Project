package frontend.components

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.`$$`
import frontend.extensions.findByOrFail

/**
 * Компонент навигационной шапки.
 * Локаторы и взаимодействие с элементами хедера.
 */
class HeaderComponent {

    private val logoLink = `$`("[data-test-id=\"nav-link-home\"]")
    private val titleText = `$`(".header .title") // "Brew & Bean"
    private val linksHeader = `$$`("[data-test-group=\"nav-link\"]")
    private val cartButton = `$`("[data-test-id=\"nav-link-cart\"]")
    private val joinButton = `$`("[data-test-id=\"nav-link-auth\"]")

    fun checkTitleText(expectedText: String) {
        titleText.shouldHave(text(expectedText))
    }

    fun clickLogo() {
        logoLink.click()
    }

    fun clickLink(linkName: String) {
        linksHeader.findByOrFail(text(linkName), "Ссылка навигации '$linkName' не найдена в хедере!").click()
    }

    fun clickCart() {
        cartButton.click()
    }

    fun clickJoin() {
        joinButton.click()
    }
}
