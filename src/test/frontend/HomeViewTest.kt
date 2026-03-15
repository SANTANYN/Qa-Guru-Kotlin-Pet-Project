package frontend

import frontend.helpers.BaseUiTest
import frontend.pages.HomeViewPage
import frontend.pages.ProductsPage
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Owner
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@Epic("Frontend Tests")
@Feature("Home View")
@Owner("mikhail")
class HomeViewTest : BaseUiTest() {

    private val homePage = HomeViewPage()
    private val productsPage = ProductsPage()

    @Test
    @Story("Main elements visibility")
    @DisplayName("Should display popular products title")
    fun `should display popular products title`() {
        homePage
            .openPage()
            .checkPopularProductsTitleText("Popular Products (first 4 products \uD83E\uDD23)")
    }

    @Test
    @Story("Main elements visibility")
    @DisplayName("Should display popular products list correctly")
    fun `should display popular products list correctly`() {
        homePage.openPage()

        // Verify there are exactly 4 products initially loaded
        homePage.checkProductsCount(4)

        // Use the products container to check the first product details
        val firstProduct = homePage.products().getCardByIndex(0)
        firstProduct.checkName("Espresso")
        firstProduct.checkPrice("$2.5")
    }

    @Test
    @Story("Main elements visibility")
    @DisplayName("Should display popular products list using data-centric model")
    fun `should display popular products list correctly using data items`() {
        homePage.openPage()

        val items = homePage.getPopularItems().getItems()
        
        // Check first product details from data object
        val espresso = items[0]
        assert(espresso.name == "Espresso")
        assert(espresso.price == 2.5f)
        assert(espresso.quantity >= 0)
    }

    @Test
    @Story("Main elements visibility")
    @DisplayName("Should display main image and welcome text")
    fun `should display main image and welcome text on home page`() {
        homePage
            .openPage()
            .checkMainImageVisible()
            .checkMainImageText("Welcome to Brew & Bean")
    }

    @Test
    @Story("Navigation")
    @DisplayName("Should navigate to Products page via header link")
    fun `should navigate to Products page via header link`() {
        homePage
            .openPage()
            .clickProductsLink()
        productsPage.checkProductsTitleText("All Products")
    }
}
