package frontend.pages

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.`$`
import io.qameta.allure.Step

class ProductsPage {

    private val productsTitle = `$`("[data-test-id=\"products-title\"]")

    private val productsItems = frontend.components.list.ProductItemsList()

    @Step("Получить заголовок страницы товаров")
    fun getTitle(): String = productsTitle.text()

    @Step("Получить список товаров на странице Products")
    fun getProductsItems(): List<frontend.components.list.ProductItem> = productsItems.getItems()

    @Step("Открыть страницу товаров")
    fun openPage(): ProductsPage {
        com.codeborne.selenide.Selenide.open("/products")
        return this
    }

    @Step("Проверить заголовок страницы товаров: {expectedText}")
    fun checkProductsTitleText(expectedText: String): ProductsPage {
        productsTitle.shouldHave(text(expectedText))
        return this
    }
}
