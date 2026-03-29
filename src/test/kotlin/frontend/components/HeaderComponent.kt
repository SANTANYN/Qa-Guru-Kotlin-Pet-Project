package frontend.components

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.`$$`
import frontend.components.popup.CreateUserPopup
import frontend.helpers.findByTestIdOrFail
import frontend.helpers.Wrappers.Companion.byDataTestGroup
import frontend.helpers.Wrappers.Companion.byDataTestId
import io.qameta.allure.Step

/**
 * Компонент навигационной шапки.
 * Локаторы и взаимодействие с элементами хедера.
 */
class HeaderComponent {

    /** Пункты основной навигации в шапке — соответствуют `data-test-id` в `Header.vue`. */
    enum class NavLink(val dataTestId: String, val label: String) {
        PRODUCTS("nav-link-products", "Products"),
        ORDERS("nav-link-orders", "Orders"),
        CONTACT("nav-link-contact", "Contact"),
        CART("nav-link-cart", "Cart"),
        JOIN("nav-link-auth", "Join")
    }

    companion object {
        /** Список всех основных пунктов навигации для обхода и проверок. */
        val NAV_LINKS: List<NavLink> = NavLink.entries
    }

    private val logoLink = `$`(byDataTestId("nav-link-home"))
    private val titleText = `$`(".header .title")
    private val linksHeader = `$$`(byDataTestGroup("nav-link"))
    private val cartButton = `$`(byDataTestId("nav-link-cart"))
    private val joinButton = `$`(byDataTestId("nav-link-auth"))

    @Step("Получить текст заголовка в хедере")
    fun getTitleText(): String = titleText.text()

    @Step("Нажать на логотип")
    fun clickLogo() {
        logoLink.click()
    }

    /**
     * Клик по пункту навигации: поиск в коллекции `nav-link` по [NavLink.dataTestId] через [findByTestIdOrFail].
     */
    @Step("Нажать на пункт навигации '{link.label}' в хедере")
    fun clickNavLink(link: NavLink) {
        linksHeader.findByTestIdOrFail(link.dataTestId).click()
    }

    /** Тексты основных пунктов навигации в порядке [NAV_LINKS]. */
    fun getPrimaryNavLabels(): List<String> =
        NAV_LINKS.map { linksHeader.findByTestIdOrFail(it.dataTestId).text() }

    @Step("Нажать на кнопку корзины")
    fun clickCart() {
        cartButton.click()
    }

    @Step("Нажать на кнопку Join")
    fun clickJoin(): CreateUserPopup {
        joinButton.click()
        return CreateUserPopup()
    }
}
