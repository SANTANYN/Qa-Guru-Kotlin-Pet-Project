package frontend

import frontend.pages.HomeViewPage
import frontend.pages.ProductsPage
import org.junit.jupiter.api.Test

class HomeViewTest {

    private val homePage = HomeViewPage()
    private val productsPage = ProductsPage()

    @Test
    fun `should display popular products title`() {
        homePage
            .openPage()
            .checkPopularProductsTitleText("Popular Products (first 4 products \uD83E\uDD23)")
    }

    @Test
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
    fun `should display main image and welcome text on home page`() {
        homePage
            .openPage()
            .checkMainImageVisible()
            .checkMainImageText("Welcome to Brew & Bean")
    }

    @Test
    fun `should navigate to Products page via header link`() {
        homePage
            .openPage()
            .clickProductsLink()
        productsPage.checkProductsTitleText("All Products")
    }
}
