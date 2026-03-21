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
import io.kotest.matchers.shouldBe
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.should
import com.codeborne.selenide.Condition.visible
import org.junit.jupiter.api.Test

@Epic("Frontend Tests")
@Feature("Main Page")
@Owner("mikhail")
class MainPageTest : BaseUiTest() {

    @Test
    @Story("Main elements visibility")
    @DisplayName("Должен отображаться заголовок популярных товаров")
    fun `should display popular products title`() {
        MainPage()
            .openPage()
            .getPopularProductsTitleText() shouldBe "Popular Products (first 4 products \uD83E\uDD23)"
    }

    @Test
    @Story("Main elements visibility")
    @DisplayName("Должен корректно отображаться список популярных товаров")
    fun `should display popular products list correctly`() {
        val page = MainPage().openPage()
        page.getProductsCount() shouldBe 4

        val firstProduct = page.products().getCardByIndex(0)
        firstProduct.getNameText() shouldBe "Espresso"
        firstProduct.getPriceText() shouldBe "$2.5"
    }

    @Test
    @Story("Main elements visibility")
    @DisplayName("Должен отображаться список популярных товаров через модель данных")
    fun `should display popular products via data model`() {
        val items = MainPage()
            .openPage()
            .getPopularProducts()
        
        val espresso = items[0]
        espresso.name shouldBe "Espresso"
        espresso.price shouldBeExactly 2.5f
        espresso.quantity shouldBeGreaterThanOrEqual 0
    }

    @Test
    @Story("Main elements visibility")
    @DisplayName("Должен отображаться главный баннер и приветственный текст")
    fun `should display main image and welcome text on home page`() {
        val page = MainPage().openPage()
        page.getMainImage().getElement().shouldBe(visible)
        page.getMainImageText() shouldBe "Welcome to Brew & Bean"
    }

    @Test
    @Story("Navigation")
    @DisplayName("Должен переходить на страницу товаров через ссылку в хедере")
    fun `should navigate to Products page via header link`() {
        MainPage()
            .openPage()
            .clickProductsLink()
        
        ProductsPage().getTitle() shouldBe "All Products"
    }
}
