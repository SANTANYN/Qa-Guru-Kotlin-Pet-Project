package frontend

import frontend.helpers.BaseUiTest
import frontend.pages.MainPage
import frontend.pages.ProductsPage
import io.kotest.matchers.shouldBe
import io.kotest.matchers.floats.shouldBeExactly
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Owner
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@Epic("Frontend Tests")
@Feature("Main Page")
@Owner("mikhail")
class MainPageTest : BaseUiTest() {

    private val mainPage = MainPage()
    private val productsPage = ProductsPage()

    @Test
    @Story("Main elements visibility")
    @DisplayName("Должен отображаться заголовок популярных товаров")
    fun `should display popular products title`() {
        mainPage
            .openPage()
            .checkPopularProductsTitleText("Popular Products (first 4 products \uD83E\uDD23)")
    }

    @Test
    @Story("Main elements visibility")
    @DisplayName("Должен корректно отображаться список популярных товаров")
    fun `should display popular products list correctly`() {
        mainPage.openPage()

        mainPage.checkProductsCount(4)

        val firstProduct = mainPage.products().getCardByIndex(0)
        firstProduct.checkName("Espresso")
        firstProduct.checkPrice("$2.5")
    }

    @Test
    @Story("Main elements visibility")
    @DisplayName("Должен отображаться список популярных товаров через модель данных")
    fun `should display popular products via data model`() {
        mainPage.openPage()

        val items = mainPage.getPopularProducts()
        val espresso = items[0]

        espresso.name shouldBe "Espresso"
        espresso.price shouldBeExactly 2.5f
        espresso.quantity shouldBeGreaterThanOrEqual 0
    }

    @Test
    @Story("Main elements visibility")
    @DisplayName("Должен отображаться главный баннер и приветственный текст")
    fun `should display main image and welcome text on home page`() {
        mainPage
            .openPage()
            .checkMainImageVisible()
            .checkMainImageText("Welcome to Brew & Bean")
    }

    @Test
    @Story("Navigation")
    @DisplayName("Должен переходить на страницу товаров через ссылку в хедере")
    fun `should navigate to Products page via header link`() {
        mainPage
            .openPage()
            .clickProductsLink()
        productsPage.checkProductsTitleText("All Products")
    }
}
