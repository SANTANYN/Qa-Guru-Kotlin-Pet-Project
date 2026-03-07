package frontend

import frontend.helpers.BaseUiTest
import frontend.pages.HomeViewPage
import frontend.pages.ProductsPage
import org.junit.jupiter.api.Test

class HomeViewTest : BaseUiTest() {

    private val homePage = HomeViewPage()
    private val productsPage = ProductsPage()

    @Test
    fun `should display popular products title`() {
        homePage
            // Removed repeated openPage() because BaseUiTest handles it
            .checkPopularProductsTitleText("Popular Products (first 4 products \uD83E\uDD23)")
    }

    @Test
    fun `should display popular products list correctly`() {
        // Removed repeated openPage()

        // Verify there are exactly 4 products initially loaded
        homePage.checkProductsCount(4)

        // Use the products container to check the first product details
        val firstProduct = homePage.products().getCardByIndex(0)
        firstProduct.checkName("Espresso")
        firstProduct.checkPrice("$2.5")
    }

    @Test
    fun `should display main image and welcome text on home page`() {
        homePage
            .checkMainImageVisible()
            .checkMainImageText("Welcome to Brew & Bean")
    }

    @Test
    fun `should navigate to Products page via header link`() {
        homePage
            .clickProductsLink()
        productsPage.checkProductsTitleText("All Products")
    }
}
