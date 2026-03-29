package frontend.pages

import com.codeborne.selenide.Selenide.`$`
import frontend.components.list.ProductItemsList
import io.qameta.allure.Step

class ProductsPage : BasePage() {

    private val productsTitle = `$`("[data-test-id=\"products-title\"]")

    @Step("Получить заголовок страницы товаров")
    fun getTitle(): String = productsTitle.text()

    @Step("Получить список товаров на странице Products")
    fun getProductsItems(): List<frontend.components.list.ProductItem> = productItemsList().getItems()

    fun productItemsList(): ProductItemsList = ProductItemsList()

    @Step("Открыть страницу товаров")
    fun openPage(): ProductsPage {
        com.codeborne.selenide.Selenide.open("/products")
        return this
    }

}
