package frontend

import frontend.helpers.BaseUiTest
import frontend.pages.HomeViewPage
import frontend.pages.ProductsPage
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Owner
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.shouldBe
import com.codeborne.selenide.Condition.visible
import org.junit.jupiter.api.Test

@Epic("Frontend Tests")
@Feature("Home View")
@Owner("mikhail")
class HomeViewTest : BaseUiTest() {

    @Test
    @Story("Main elements visibility")
    @DisplayName("Should display popular products title")
    fun `should display popular products title`() {
        HomeViewPage()
            .openPage()
            .getPopularProductsTitleText() shouldBe "Popular Products (first 4 products \uD83E\uDD23)"
    }

    @Test
    @Story("Main elements visibility")
    @DisplayName("Should display popular products list correctly")
    fun `should display popular products list correctly`() {
        val page = HomeViewPage().openPage()

        // Verify there are exactly 4 products initially loaded
        page.getProductsCount() shouldBe 4

        // Use the products container to check the first product details
        val firstProduct = page.products().getCardByIndex(0)
        firstProduct.getNameText() shouldBe "Espresso"
        firstProduct.getPriceText() shouldBe "$2.5"
    }

    @Test
    @Story("Main elements visibility")
    @DisplayName("Should display popular products list using data-centric model")
    fun `should display popular products list correctly using data items`() {
        val items = HomeViewPage()
            .openPage()
            .popularItems().getItems()
        
        // Check first product details from data object
        val espresso = items[0]
        espresso.name shouldBe "Espresso"
        espresso.price shouldBe 2.5f
        espresso.quantity shouldBeGreaterThanOrEqual 0
    }

    @Test
    @Story("Main elements visibility")
    @DisplayName("Should display main image and welcome text")
    fun `should display main image and welcome text on home page`() {
        val page = HomeViewPage().openPage()
        page.mainImage().getElement().shouldBe(visible)
        page.getMainImageText() shouldBe "Welcome to Brew & Bean"
    }

    @Test
    @Story("Navigation")
    @DisplayName("Should navigate to Products page via header link")
    fun `should navigate to Products page via header link`() {
        HomeViewPage()
            .openPage()
            .clickProductsLink()

        ProductsPage().getTitle() shouldBe "All Products"
    }
}
