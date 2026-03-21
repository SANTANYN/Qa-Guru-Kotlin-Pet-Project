package frontend

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.WebDriverRunner
import frontend.helpers.BaseUiTest
import frontend.helpers.Wrappers.Companion.byDataTestId
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
    @DisplayName("Check all expected links are present in header")
    @Story("Navigation links")
    fun `should have all expected links in header`() {
        val expectedLinks = listOf("Products", "Orders", "Contact", "Cart", "Join")
        HomeViewPage()
            .openPage()
            .header().getLinksTexts() shouldContainAll expectedLinks
    }

    @DisplayName("Header link opens correct page or panel")
    @ParameterizedTest(name = "{0} → URL содержит {1}")
    @Story("Navigation links")
    @CsvSource(
        "Products, /products",
        "Orders, /orders",
        "Contact, /contact"
    )
    fun `should navigate when clicking header link`(linkName: String, urlPart: String) {
        HomeViewPage()
            .openPage()
            .header()
            .clickLink(linkName)

        WebDriverRunner.url() shouldContain urlPart

        when (linkName) {
            "Products" -> ProductsPage().getTitle() shouldBe "All Products"
            "Contact" -> `$`(".contact-title").shouldBe(visible).text() shouldBe "Testing Playground Frontend"
            "Orders" -> `$`("md-outlined-text-field").shouldBe(visible)
        }
    }

    @Test
    @DisplayName("Cart opens checkout panel in header")
    @Story("Navigation links")
    fun `should open cart panel when clicking Cart`() {
        HomeViewPage()
            .openPage()
            .header()
            .clickLink("Cart")

        `$`(byDataTestId("cart-checkout")).shouldBe(visible)
    }

    @Test
    @DisplayName("Join opens create account dialog")
    @Story("Navigation links")
    fun `should open create account dialog when clicking Join`() {
        HomeViewPage()
            .openPage()
            .clickJoin()

        `$`(byDataTestId("create-title")).shouldBe(visible)
        `$`(byDataTestId("create-title")).text() shouldBe "Create Account"
    }

    @DisplayName("Each expected link text is present in header")
    @ParameterizedTest(name = "Link: {0}")
    @Story("Navigation links")
    @ValueSource(strings = ["Products", "Orders", "Contact", "Cart", "Join"])
    fun `should have link text visible in header`(linkName: String) {
        HomeViewPage()
            .openPage()
            .header().getLinksTexts().contains(linkName) shouldBe true
    }
}
