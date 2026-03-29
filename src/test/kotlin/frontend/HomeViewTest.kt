package frontend

import frontend.helpers.BaseUiTest
import frontend.pages.HomeViewPage
import frontend.pages.ProductsPage
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.shouldBe
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

    @Test
    @Story("Main elements visibility")
    @DisplayName("Should display popular products title")
    fun shouldDisplayPopularProductsTitle() {
        HomeViewPage()
            .openPage()
            .getPopularProductsTitleText() shouldBe "Popular Products (first 4 products \uD83E\uDD23)"
    }

    @Test
    @Story("Main elements visibility")
    @DisplayName("Should display popular products list correctly")
    fun shouldDisplayPopularProductsListCorrectly() {
        val page = HomeViewPage().openPage()

        page.getProductsCount() shouldBe 4

        val firstProduct = page.products().getCardByIndex(0)
        firstProduct.getNameText() shouldBe "Coca Cola"
        firstProduct.getPriceText() shouldBe "$2.33"
    }

    @Test
    @Story("Main elements visibility")
    @DisplayName("Should display popular products list using data-centric model")
    fun shouldDisplayPopularProductsListUsingDataItems() {
        val items = HomeViewPage()
            .openPage()
            .popularItems().getItems()

        val first = items[0]
        first.name shouldBe "Coca Cola"
        first.price shouldBe 2.33f
        first.quantity shouldBeGreaterThanOrEqual 0
    }

    @Test
    @Story("Main elements visibility")
    @DisplayName("Should display main image and welcome text")
    fun shouldDisplayMainImageAndWelcomeText() {
        val page = HomeViewPage().openPage()
        val mainImageVisible = page.mainImage().isDisplayed()
        mainImageVisible shouldBe true
        page.getMainImageText() shouldBe "Welcome to Brew & Bean"
    }

    @Test
    @Story("Navigation")
    @DisplayName("Should navigate to Products page via header link")
    fun shouldNavigateToProductsPageViaHeaderLink() {
        HomeViewPage()
            .openPage()
            .clickProductsLink()

        ProductsPage().getTitle() shouldBe "All Products"
    }
}
