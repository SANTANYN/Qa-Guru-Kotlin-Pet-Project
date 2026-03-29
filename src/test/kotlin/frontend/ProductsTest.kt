package frontend

import frontend.components.HeaderComponent
import frontend.helpers.BaseUiTest
import frontend.pages.MainPage
import frontend.pages.ProductsPage
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.equals.shouldBeEqual
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
            .navigateToHeader().clickNavLink(HeaderComponent.NavLink.PRODUCTS)

        ProductsPage().getTitle() shouldBe "All Products"
    }

    @Test
    @Story("Products comparison")
    @DisplayName("Проверка что первый популярный продукт совпадает с первым продуктом на странице Products")
    fun testFirstPopularProductMatchesFirstProductsItem() {
        val firstPopularProduct = MainPage()
            .openPage()
            .getPopularProducts()[0]

        val productsPage = ProductsPage()
        MainPage()
            .navigateToHeader()
            .clickNavLink(HeaderComponent.NavLink.PRODUCTS)

        val firstProductsItem = productsPage.getProductsItems()[0]

        // Стандартные проверки равенства данных (через объекты Kotest благодаря переопределенному equals)
        firstProductsItem shouldBeEqual firstPopularProduct

        val controlsVisible = firstProductsItem.areInteractiveControlsVisible()
        controlsVisible shouldBe true
    }

    @Test
    @Story("Products comparison")
    @DisplayName("Проверка что все популярные продукты присутствуют на странице Products")
    fun testAllPopularProductsPresentOnProductsPage() {
        // Получаем все популярные товары с главной страницы
        val popularProducts = MainPage()
            .openPage()
            .getPopularProducts()

        // Переходим на страницу всех товаров
        MainPage()
            .navigateToHeader()
            .clickNavLink(HeaderComponent.NavLink.PRODUCTS)

        // Получаем все товары со страницы Products
        val allProducts = ProductsPage().getProductsItems()

        // Проверяем что для каждого популярного товара найдется точная копия на странице продуктов
        allProducts shouldContainAll popularProducts
    }
}
