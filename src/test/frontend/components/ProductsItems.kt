package frontend.components

import com.codeborne.selenide.CollectionCondition
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.SelenideElement
import frontend.extensions.toPrice
import frontend.helpers.Wrappers.Companion.byDataTestGroup
import io.qameta.allure.Step

/**
 * Data-класс товара на странице Products.
 */
data class ProductsItem(
    val image: SelenideElement,
    val name: String,
    val description: String,
    val price: Float,
    val btnIncrement: SelenideElement,
    val quantity: Int,
    val btnDecrement: SelenideElement
)

/**
 * Компонент-коллекция для работы со списком товаров на странице Products.
 */
class ProductsItems {

    private val listProducts = `$$`(byDataTestGroup("product-card"))

    @Step("Получить список товаров на странице Products")
    fun getItems(): List<ProductsItem> {
        listProducts.shouldHave(CollectionCondition.sizeGreaterThan(0))

        return listProducts.map { card ->
            ProductsItem(
                image = card.`$`(byDataTestGroup("product-card-image")),
                name = card.`$`(byDataTestGroup("product-card-name")).text(),
                description = card.`$`(byDataTestGroup("product-card-description")).text(),
                price = card.`$`(byDataTestGroup("product-card-price")).text().toPrice(),
                btnIncrement = card.`$`(byDataTestGroup("product-card-increment")),
                quantity = card.`$`(byDataTestGroup("product-card-qty")).text().toInt(),
                btnDecrement = card.`$`(byDataTestGroup("product-card-decrement"))
            )
        }
    }
}
