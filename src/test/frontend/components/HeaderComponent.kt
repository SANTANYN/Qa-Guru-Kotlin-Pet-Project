package frontend.components

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.`$$`
import frontend.extensions.findByOrFail
import frontend.helpers.Wrappers.Companion.byDataTestGroup
import frontend.helpers.Wrappers.Companion.byDataTestId

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
