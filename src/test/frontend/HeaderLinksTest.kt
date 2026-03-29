package frontend

import frontend.components.HeaderComponent
import frontend.helpers.BaseUiTest
import frontend.pages.HomeViewPage
import frontend.pages.ProductsPage
import io.kotest.matchers.shouldBe
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.string.shouldContain
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Owner
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

@Epic("Frontend Tests")
@Feature("Header Navigation")
@Owner("mikhail")
class HeaderLinksTest : BaseUiTest() {

    @Test
    @DisplayName("All expected navigation link labels are present in the header")
    @Story("Navigation links")
    fun shouldHaveAllExpectedHeaderLinks() {
        val expectedLinks = HeaderComponent.NAV_LINKS.map { it.label }
        val navLabels = HomeViewPage()
            .openPage()
            .navigateToHeader()
            .getPrimaryNavLabels()
        navLabels shouldContainAll expectedLinks
    }

    @DisplayName("Clicking a header link navigates to the expected URL path")
    @ParameterizedTest(name = "{0} → URL contains {1}")
    @Story("Navigation links")
    @CsvSource(
        "PRODUCTS, /products",
        "ORDERS, /orders",
        "CONTACT, /contact"
    )
    fun headerNavigationChangesUrl(nav: String, urlPart: String) {
        val link = HeaderComponent.NavLink.valueOf(nav)
        HomeViewPage()
            .openPage()
            .navigateToHeader()
            .clickNavLink(link)

        val currentUrl = ProductsPage().getUrl()
        currentUrl shouldContain urlPart
    }

    @Test
    @DisplayName("Cart opens panel with checkout button visible")
    @Story("Navigation links")
    fun shouldOpenCartPanelWithCheckoutVisible() {
        HomeViewPage()
            .openPage()
            .navigateToHeader()
            .clickNavLink(HeaderComponent.NavLink.CART)

        val checkoutVisible = HomeViewPage().isCartCheckoutButtonVisible()
        checkoutVisible shouldBe true
    }

    @Test
    @DisplayName("Join opens create account dialog with expected title")
    @Story("Navigation links")
    fun shouldOpenCreateAccountDialogWhenClickingJoin() {
        val createPopup = HomeViewPage()
            .openPage()
            .clickJoin()

        val dialogOpen = createPopup.isOpen()
        val titleText = createPopup.getTitleText()

        dialogOpen shouldBe true
        titleText shouldBe "Create Account"
    }

    @DisplayName("Each header link label from list is present in primary nav labels")
    @ParameterizedTest(name = "Link label: {0}")
    @Story("Navigation links")
    @ValueSource(
        strings = ["Products", "Orders", "Contact", "Cart", "Join"]
    )
    fun headerContainsLinkLabel(expectedLinkLabel: String) {
        val navLabels = HomeViewPage()
            .openPage()
            .navigateToHeader()
            .getPrimaryNavLabels()
        val containsLabel = navLabels.contains(expectedLinkLabel)
        containsLabel shouldBe true
    }
}
