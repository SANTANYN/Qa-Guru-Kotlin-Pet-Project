package frontend.components.list

import com.codeborne.selenide.CollectionCondition
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.SelenideElement
import frontend.helpers.toPrice
import frontend.helpers.Wrappers.Companion.byDataTestGroup
import io.qameta.allure.Step

/**
 * Data-класс для представления состояния товара.
 */
data class ProductItem(
    val image: SelenideElement,
    val name: String,
    val description: String,
    val price: Float,
    val btnIncrement: SelenideElement,
    val quantity: Int,
    val btnDecrement: SelenideElement
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProductItem

        if (name != other.name) return false
        if (description != other.description) return false
        if (price != other.price) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + price.hashCode()
        return result
    }
}

/**
 * Компонент-коллекция товаров для работы со списками.
 */
class ProductItemsList {

    private val listProducts = `$$`(byDataTestGroup("product-card"))

    @Step("Получить список товаров")
    fun getItems(): List<ProductItem> {
        listProducts.shouldHave(CollectionCondition.sizeGreaterThan(0))

        return listProducts.map { card ->
            ProductItem(
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
