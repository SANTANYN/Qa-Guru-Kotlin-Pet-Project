package frontend

import frontend.components.PopularItem
import frontend.helpers.BaseUiTest
import frontend.pages.MainPage
import frontend.pages.ProductsPage
import io.kotest.matchers.equality.shouldBeEqualToDifferentTypeIgnoringFields
import io.kotest.matchers.shouldBe
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Owner
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@Epic("Frontend Tests")
@Feature("Products")
@Owner("mikhail")
class ProductsTest : BaseUiTest() {

    @Test
    @Story("Products comparison")
    @DisplayName("Проверка заголовка страницы Products")
    fun testProductsPageTitle() {
        MainPage()
            .openPage()
            .header().clickLink("Products")

        val title = ProductsPage().getTitle()
        title shouldBe "All Products"
    }

    @Test
    @Story("Products comparison")
    @DisplayName("Проверка что первый популярный продукт совпадает с первым продуктом на странице Products")
    fun testFirstPopularProductMatchesFirstProductsItem() {
        val firstPopularProduct = MainPage()
            .openPage()
            .getPopularProducts()[0]

        MainPage()
            .header()
            .clickLink("Products")

        val txtTitle = ProductsPage().getTitle()
        txtTitle shouldBe "All Products"

        val firstProductsItem = ProductsPage()
            .getProductsItems()[0]

        firstPopularProduct.shouldBeEqualToDifferentTypeIgnoringFields(
            firstProductsItem,
            PopularItem::image,
            PopularItem::btnIncrement,
            PopularItem::btnDecrement,
        )
    }
}
